package place.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import place.common.CommonUtil;
import place.dto.entity.UserInfo;
import place.exception.NoUserContentException;
import place.repository.UserRepository;

import java.util.Optional;

@Service
public class LoginService {

    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    UserRepository userRepository;


    public boolean validateLogin(String userId, String userPassword) throws NoUserContentException {

        Optional<UserInfo> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return user.get().getPassword().equals(CommonUtil.Encrypt.encrypt(userPassword));
        } else {
            throw new NoUserContentException("User Id no content");
        }
    }
}
