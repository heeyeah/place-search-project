package place.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT)
public class NoUserContentException extends BaseException {

    private static final long serialVersionUID = 1L;

    public NoUserContentException(String message) {
        super(message);
    }
}
