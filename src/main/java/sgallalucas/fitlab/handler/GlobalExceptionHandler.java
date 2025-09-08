package sgallalucas.fitlab.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sgallalucas.fitlab.dtos.errors.ErrorResponseDetails;
import sgallalucas.fitlab.dtos.errors.FieldErrorDetails;
import sgallalucas.fitlab.exceptions.DuplicateRecordException;
import sgallalucas.fitlab.exceptions.NotAllowedOperationException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<ErrorResponseDetails> handleDuplicateRecordException(DuplicateRecordException e) {
        ErrorResponseDetails details = new ErrorResponseDetails(
                HttpStatus.CONFLICT.value(),
                "Validation error",
                List.of(new FieldErrorDetails("email", e.getMessage()))
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(details);
    }

    @ExceptionHandler(NotAllowedOperationException.class)
    public ResponseEntity<ErrorResponseDetails> handleNotAllowedOperationException(NotAllowedOperationException e) {
        ErrorResponseDetails details = new ErrorResponseDetails(
                HttpStatus.BAD_REQUEST.value(),
                "Not allowed",
                List.of(new FieldErrorDetails("", e.getMessage()))
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(details);
    }
}
