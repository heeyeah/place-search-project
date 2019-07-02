package place.common;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import place.JpaRepositorySupport;
import place.dto.entity.UserInfo;
import place.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@DisplayName("repository 테스트")
public class RepositoryTest extends JpaRepositorySupport {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("암호화된 비밀번호값 저장 확인 테스트")
    public void EncryptPasswordSavingTest() {

        List<UserInfo> list = userRepository.findAll();
        for(UserInfo user : list) {
            System.out.printf("===== userID : %s, userPW : %s =====\n", user.getUserId(), user.getPassword());
            assertEquals(CommonUtil.Encrypt.encrypt(user.getUserId()), user.getPassword());
        }
    }
}
