package place.exception;

public class BaseException extends Exception {

    private static final long serialVersionUID = 1L;

    private String message;

    public BaseException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
