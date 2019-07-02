package place.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorDetailBody {

    private LocalDateTime timestamp;
    private String message;
    private int status;
    private String error;

    public ErrorDetailBody(LocalDateTime timestamp, String message, HttpStatus httpStatus) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }


    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "ErrorDetailBody{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", error='" + error + '\'' +
                '}';
    }
}

