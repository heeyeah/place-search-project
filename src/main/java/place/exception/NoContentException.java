package place.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Invalid user ID")
public class NoContentException extends BaseException {

    private static final long serialVersionUID = 1L;

    public NoContentException(String message) {
        super(message);
    }
}
