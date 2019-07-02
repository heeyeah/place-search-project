package place.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    private static HttpSession httpSession;

    public static HttpSession getHttpSession() {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        if(httpSession == null || !attr.getRequest().isRequestedSessionIdValid()) {
            httpSession = attr.getRequest().getSession(true);
        }

        return httpSession;
    }

    public static String getSessionUserId() {
        return (String) getHttpSession().getAttribute(Constants.SESSION_USER_ID);
    }

    public static void setSessionUserId(String userId) {
        getHttpSession().setAttribute(Constants.SESSION_USER_ID, userId);
    }
}
