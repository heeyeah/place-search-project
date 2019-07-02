package place.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import place.dto.entity.SearchHistoryStatistics;
import place.dto.entity.SearchHistory;

import java.util.List;

public interface SearchRepository extends JpaRepository<SearchHistory, String> {

    List<SearchHistory> findByUserId(String userId);

    @Query(value = "select keyword as keyword, count(keyword) as count from SEARCH_HISTORY GROUP BY KEYWORD ORDER BY COUNT DESC limit 10", nativeQuery = true)
    List<SearchHistoryStatistics> getStatisticsGroupByKeyword();
}
