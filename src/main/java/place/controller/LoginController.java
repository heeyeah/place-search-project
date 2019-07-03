package place.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import place.service.LoginService;

@RestController
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	LoginService loginService;


	@GetMapping("/login")
	public ResponseEntity<Boolean> login(@RequestParam("userId") String userId, @RequestParam("userPassword") String userPassword) throws Exception {

		logger.info(" /login api start. user [{}]", userId);

		if(userId == null || userPassword == null) {
			throw new IllegalArgumentException();
		}

		boolean isValid = loginService.validateLogin(userId, userPassword);

		return new ResponseEntity<>(isValid, HttpStatus.OK);
	}
}
