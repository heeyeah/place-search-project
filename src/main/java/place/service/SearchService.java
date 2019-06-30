package place.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import place.common.Constants;
import place.common.SessionUtil;
import place.dto.PlaceList;
import place.entity.SearchHistoryStatistics;
import place.entity.SearchHistory;
import place.dto.kakao.format.KakaoResponse;
import place.repository.SearchRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class SearchService {

    private final Logger logger = LoggerFactory.getLogger(SearchService.class);

    private static final String QUERY = "query";
    private static final String PAGE = "page";
    private static final String SIZE = "size";
    private static final String AUTHORIZATION = "Authorization";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SearchRepository searchRepository;

    public List<SearchHistoryStatistics> getStatisticsGroupByKeyword() {
        return searchRepository.getStatisticsGroupByKeyword();
    }

    private void saveSearchHistory(String keyword) {
        searchRepository.save(new SearchHistory(SessionUtil.getSessionUserId(), keyword, LocalDateTime.now()));
    }

    public List<SearchHistory> getSearchHistory(String userId) {
        List<SearchHistory> list = searchRepository.findByUserId(userId);
        list.sort(Comparator.comparing(SearchHistory::getSearchDttm).reversed());
        return list;
    }

    public PlaceList searchPlaceByKeyword(String keyword, int page, int size) throws IOException {

        // header setting
        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION, Constants.KAKAO_APP_KEY);


        // uri
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(Constants.KAKAO_SEARCH_API)
                .path(Constants.KAKAO_SEARCH_PATH)
                .queryParam(QUERY, keyword)
                .queryParam(PAGE, page)
                .queryParam(SIZE, size)
                .build();


        // request
        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                uriComponents.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        ObjectMapper mapper = new ObjectMapper();
        KakaoResponse kakaoResponse = mapper.readValue(response.getBody(), KakaoResponse.class);

        int pageableCount = kakaoResponse.getMeta().getPageableCount();
        int totalPage = (pageableCount % size == 0) ? pageableCount / size : (pageableCount / size) + 1;


        // redirectUrl setting
        kakaoResponse.getDocuments().stream().forEach(document -> {
            document.setRedirectUrl(Constants.MAP_REDIRECT_URL + document.getId());
        });

        // output setting
        PlaceList placeList = new PlaceList(page, totalPage, size, pageableCount, kakaoResponse.getDocuments());

        // save search history
        this.saveSearchHistory(keyword);

        return placeList;
    }

}
