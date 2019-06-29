package place;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("로그인테스트")
public class LoginControllerTest extends SpringMockMvcTestSupport {


    @Test
    @DisplayName("로그인 성공")
    void loginTest() throws Exception {

        this.mockMvc.perform(get("/login")
                .param("userId", "USER00")
                .param("userPassword", "USER00"))
                .andExpect(status().isOk())
                .andExpect(content().string(Boolean.toString(true)))
                .andDo(print());

    }

    @Test
    @DisplayName("로그인 실패")
    void loginTest2() throws Exception {

        this.mockMvc.perform(get("/login")
                .param("userId", "USER00")
                .param("userPassword", "USER01"))
                .andExpect(status().isOk())
                .andExpect(content().string(Boolean.toString(false)))
                .andDo(print());

    }
}
