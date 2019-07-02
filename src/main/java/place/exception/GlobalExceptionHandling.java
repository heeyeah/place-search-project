package place.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {

    private Logger logger =LoggerFactory.getLogger(getClass());

    @ExceptionHandler(NoUserContentException.class)
    public ResponseEntity<?> handleNoUserContentException(NoUserContentException ex, WebRequest request) {
        return errorResponse(ex, request, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(SessionInvalidException.class)
    public ResponseEntity<?> handleSessionInvalidException(SessionInvalidException ex, WebRequest request) {
        return errorResponse(ex, request, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<?> handleHttpClientErrorException(HttpClientErrorException ex, WebRequest request) {
        return errorResponse(ex, request, ex.getStatusCode());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<?> handleHttpServerErrorException(HttpServerErrorException ex, WebRequest request) {
        return errorResponse(ex, request, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
        return errorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<?> errorResponse(Exception ex, WebRequest request, HttpStatus httpStatus) {
        HttpHeaders headers = new HttpHeaders();
        ErrorDetailBody body = new ErrorDetailBody(LocalDateTime.now(), ex.getMessage(), httpStatus);
        logger.error("{}", ex);

        return handleExceptionInternal(ex, body, headers, httpStatus, request);
    }


}
