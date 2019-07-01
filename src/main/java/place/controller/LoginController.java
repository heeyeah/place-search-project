package place.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.ApplicationContextHeaderFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;
import place.common.Constants;
import place.common.SessionUtil;
import place.service.LoginService;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(LoginController.class);

	private static final String SESSION_ID = "SESSION_ID";

	@Autowired
	LoginService loginService;



	@GetMapping("/login")
	public ResponseEntity<Boolean> login(@RequestParam("userId") String userId, @RequestParam("userPassword") String userPassword) throws Exception {

		logger.info("id :" +userId);
		logger.info("pw : "+userPassword);

		if(userId == null || userPassword == null) {
			throw new IllegalArgumentException();
		}

		boolean isValid = loginService.validateLogin(userId, userPassword);

		if(isValid) {
			HttpSession session = SessionUtil.getHttpSession();
			session.setAttribute(Constants.SESSION_USER_ID, userId);
		}

		return new ResponseEntity<>(isValid, HttpStatus.OK);
	}



	@GetMapping("/getsession")
	public String getSession() {

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();

		return (String)session.getAttribute(Constants.SESSION_USER_ID);
	}
}
