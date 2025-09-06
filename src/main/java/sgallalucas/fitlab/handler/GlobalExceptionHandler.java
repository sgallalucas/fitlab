package sgallalucas.fitlab.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sgallalucas.fitlab.dtos.errors.ErrorResponseDetails;
import sgallalucas.fitlab.exceptions.DuplicateRecordException;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<ErrorResponseDetails> handleDuplicateRecordException(DuplicateRecordException e, HttpServletRequest request) {

        ErrorResponseDetails details = new ErrorResponseDetails(
                HttpStatus.CONFLICT.value(),
                "Validation error",
                e.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now(),
                List.of()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(details);
    }
}
