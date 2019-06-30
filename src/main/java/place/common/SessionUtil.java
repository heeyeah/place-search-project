package place.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    public static HttpSession getHttpSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true);
    }

    public static String getSessionUserId() {
        return (String) getHttpSession().getAttribute(Constants.SESSION_USER_ID);
    }
}
