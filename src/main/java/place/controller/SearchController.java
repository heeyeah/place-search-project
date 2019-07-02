package place.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import place.dto.PlaceList;
import place.dto.entity.SearchHistoryStatistics;
import place.dto.entity.SearchHistory;
import place.service.SearchService;

import java.util.List;

@RestController
public class SearchController {

    private final Logger logger = LoggerFactory.getLogger(SearchController.class);


    @Autowired
    SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<PlaceList> searchPlaceByKeyword(@RequestParam("keyword") String keyword, @RequestParam("page") int page, @RequestParam("size") int size) throws Exception {

        PlaceList list = searchService.searchPlaceByKeyword(keyword, page, size);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<SearchHistory>> getSearchHistory(@RequestParam("userId") String userId) throws Exception {

        List<SearchHistory> list = searchService.getSearchHistory(userId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/statistics")
    public ResponseEntity<List<SearchHistoryStatistics>> getStatisticsGroupByKeyword() throws Exception {

        List<SearchHistoryStatistics> list = searchService.getStatisticsGroupByKeyword();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
