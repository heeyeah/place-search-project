package place.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import place.SpringMockMvcTestSupport;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("검색테스트")
public class SearchControllerTest extends SpringMockMvcTestSupport {

    @Test
    @DisplayName("검색 - 카카오프렌즈, page 1, size 10")
    void searchByKeywordTest() throws Exception {

        this.mockMvc.perform(get("/search")
                .param("keyword", "카카오프렌즈")
                .param("page", "1")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("검색 히스토리 (최신순)")
    void getSearchHistoryByUserId() throws Exception {

        this.mockMvc.perform(get("/history")
                .param("userId", "USER00"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("통계")
    void getStatistics() throws Exception {
        this.mockMvc.perform(get("/statistics"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
