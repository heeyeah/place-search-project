package place.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorDetails {

    private LocalDateTime timestamp;
    private String message;
    private HttpStatus httpStatus;
    private int status;
    private String error;

    public ErrorDetails(LocalDateTime timestamp, String message, HttpStatus httpStatus) {
        this.timestamp = timestamp;
        this.message = message;
        this.httpStatus = httpStatus;
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
    }

    @Override
    public String toString() {
        return "ErrorDetails{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", httpStatus=" + httpStatus +
                ", status=" + status +
                ", error='" + error + '\'' +
                '}';
    }
}
