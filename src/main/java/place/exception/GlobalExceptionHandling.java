package place.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {


    private Logger logger =LoggerFactory.getLogger(getClass());


    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<?> handleNoContentException(NoContentException ex, WebRequest request) {
        return errorResponse(ex, request, HttpStatus.NO_CONTENT);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
        ex.printStackTrace();
        return errorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private ResponseEntity<?> errorResponse(Exception ex, WebRequest request, HttpStatus httpStatus) {
        HttpHeaders headers = new HttpHeaders();
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), httpStatus);
        logger.error("errorDetails : {}", errorDetails.toString());
        return handleExceptionInternal(ex, errorDetails, headers, httpStatus, request);
    }


}
