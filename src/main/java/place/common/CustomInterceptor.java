package place.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import place.exception.SessionInvalidException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CustomInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(CustomInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if(!request.isRequestedSessionIdValid()
                || StringUtils.isEmpty(request.getSession().getAttribute(Constants.SESSION_USER_ID))) {
          throw new SessionInvalidException("Session invalid.");
        }

        return true;
    }
}
