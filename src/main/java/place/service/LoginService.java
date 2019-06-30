package place.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import place.common.CommonUtil;
import place.entity.UserInfo;
import place.exception.NoContentException;
import place.repository.DataRepository;

import java.util.Optional;

@Service
public class LoginService {

    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    DataRepository dataRepository;


    public boolean validateLogin(String userId, String userPassword) throws Exception {

        Optional<UserInfo> user = dataRepository.findById(userId);

        logger.info(" valie Login");

        if (user.isPresent()) {
            logger.info("====== {}", user.get().toString());
            return user.get().getPassword().equals(CommonUtil.Encrypt.encrypt(userPassword));
        } else {
            throw new NoContentException("User Id no content");
        }
    }
}
