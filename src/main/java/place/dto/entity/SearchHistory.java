package place.dto.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="SEARCH_HISTORY")
public class SearchHistory {

    @Id
    private String uuid;

    private String userId;

    private String keyword;

    private LocalDateTime searchDttm;

    private SearchHistory() {
    }

    public SearchHistory(String userId, String keyword, LocalDateTime searchDttm) {
        this.uuid = UUID.randomUUID().toString();
        this.userId = userId;
        this.keyword = keyword;
        this.searchDttm = searchDttm;
    }

    public String getUuid() {
        return uuid;
    }

    public String getUserId() {
        return userId;
    }

    public String getKeyword() {
        return keyword;
    }

    public LocalDateTime getSearchDttm() {
        return searchDttm;
    }

    @Override
    public String toString() {
        return "SearchHistory{" +
                "uuid=" + uuid +
                ", userId='" + userId + '\'' +
                ", keyword='" + keyword + '\'' +
                ", searchDttm=" + searchDttm +
                '}';
    }
}
