package place.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class SessionInvalidException extends BaseException {

    private static final long serialVersionUID = 1L;

    public SessionInvalidException(String message) {
        super(message);
    }
}
