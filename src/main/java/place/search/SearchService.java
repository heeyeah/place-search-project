package place.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import place.common.Constants;
import place.dto.PlaceList;
import place.dto.kakao.format.KakaoResponse;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Service
public class SearchService {

    private final Logger logger = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private RestTemplate restTemplate;

    public PlaceList searchPlaceByKeyword(String keyword, int page, int size) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", Constants.KAKAO_API_APP_KEY);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(Constants.KAKAO_SEARCH_API)
                .path("v2/local/search/keyword.json")
                .queryParam("query", keyword)
                .queryParam("page", 1)
                .queryParam("size", 10);

        UriComponents uriComponents = builder.build();


        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                uriComponents.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        ObjectMapper mapper = new ObjectMapper();


        KakaoResponse kakaoResponse = mapper.readValue(response.getBody(), KakaoResponse.class);

        int pageableCount = kakaoResponse.getMeta().getPageableCount();
        int totalPage;

        if(pageableCount % size == 0) {
            totalPage = pageableCount / size;
        } else {
            totalPage = (pageableCount / size) + 1;
        }

        PlaceList list = new PlaceList(page, totalPage, size, kakaoResponse.getDocuments());

        logger.info("==================== \n{} \n=========================", kakaoResponse.toString());
        logger.info("==================== \n{} \n=========================", list.toString());

        return list;
    }

}
