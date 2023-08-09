package io.upschool.exception;
import io.upschool.dto.BaseResponse;
import io.upschool.dto.flight.FlightSaveResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.text.MessageFormat;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatusCode status,
                                                                   WebRequest request) {
        final var errorMessage =
                MessageFormat.format("No handler found for {0} {1}", ex.getHttpMethod(), ex.getRequestURL());
        System.out.println(errorMessage);
        var response = BaseResponse.<FlightSaveResponse>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(final Exception exception, final WebRequest request) {
        System.out.println("Bir hata meydana geldi Exception:" + exception.getMessage()
                + request.getHeader("client-type"));
        // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        var response = BaseResponse.<FlightSaveResponse>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }




}
