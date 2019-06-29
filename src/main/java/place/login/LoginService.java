package place.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import place.common.CommonUtil;
import place.dto.UserInfo;
import place.repository.DataRepository;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class LoginService {

    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    DataRepository dataRepository;


    public boolean validateLogin(String userId, String userPassword) {

        Optional<UserInfo> user = dataRepository.findById(userId);

        logger.info(" valie Login");

        if (user.isPresent()) {

            logger.info("====== {}", user.get().toString());

            if (user.get().getPassword().equals(CommonUtil.Encrypt.encrypt(userPassword))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
