package place.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(LoginController.class);


	@Autowired
	LoginService loginService;

	@GetMapping("/login")
	public ResponseEntity<Boolean> login(@RequestParam("userId") String userId, @RequestParam("userPassword") String userPassword) {

		boolean isValid = loginService.validateLogin(userId, userPassword);
		logger.info("========== {} / {}", userId, userPassword);
		logger.info(" ==========init ! {}", isValid);

		// TODO session 저장

		return new ResponseEntity<>(isValid, HttpStatus.OK);
	}
}
