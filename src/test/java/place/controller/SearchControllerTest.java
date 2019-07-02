package place.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import place.SpringMockMvcTestSupport;
import place.common.Constants;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("검색테스트")
public class SearchControllerTest extends SpringMockMvcTestSupport {


    @Test
    @DisplayName("검색")
    void searchByKeywordTest() throws Exception {

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute(Constants.SESSION_USER_ID, "USER00");

        for(int i = 0 ; i < 5 ; i++)
            searchByKeyword("kakao", mockHttpSession);

        for(int i = 0 ; i < 3 ; i++)
            searchByKeyword("스타벅스", mockHttpSession);

    }

    void searchByKeyword(String keyword, MockHttpSession mockHttpSession) throws Exception {

        this.mockMvc.perform(get("/search")
                .param("keyword", keyword)
                .param("page", "1")
                .param("size", "10")
                .session(mockHttpSession))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("검색 히스토리 (최신순)")
    void getSearchHistoryByUserId() throws Exception {

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute(Constants.SESSION_USER_ID, "USER00");

        this.mockMvc.perform(get("/history")
                .param("userId", "USER00")
                .session(mockHttpSession))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("TOP 10 통계")
    void getStatistics() throws Exception {

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute(Constants.SESSION_USER_ID, "USER00");

        this.mockMvc.perform(get("/statistics")
                .session(mockHttpSession))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
