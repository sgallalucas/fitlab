package sgallalucas.fitlab.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sgallalucas.fitlab.dtos.errors.ErrorResponseDetails;
import sgallalucas.fitlab.dtos.errors.FieldErrorDetails;
import sgallalucas.fitlab.exceptions.DuplicateRecordException;
import sgallalucas.fitlab.exceptions.NotAllowedOperationException;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<FieldError> fieldErrors = e.getFieldErrors();

        List<FieldErrorDetails> fieldErrorDetails = fieldErrors.stream().map(fe -> new FieldErrorDetails(
                fe.getField(), fe.getDefaultMessage())).toList();

        ErrorResponseDetails details = new ErrorResponseDetails(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation error",
                LocalDateTime.now(),
                fieldErrorDetails
        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(details);
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<ErrorResponseDetails> handleDuplicateRecordException(DuplicateRecordException e) {

        ErrorResponseDetails details = new ErrorResponseDetails(
                HttpStatus.CONFLICT.value(),
                "Validation error",
                LocalDateTime.now(),
                List.of(new FieldErrorDetails("email", e.getMessage()))
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(details);
    }

    @ExceptionHandler(NotAllowedOperationException.class)
    public ResponseEntity<ErrorResponseDetails> handleNotAllowedOperationException(NotAllowedOperationException e) {

        ErrorResponseDetails details = new ErrorResponseDetails(
                HttpStatus.BAD_REQUEST.value(),
                "Not allowed. " + e.getMessage(),
                LocalDateTime.now(),
                List.of()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(details);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDetails> handelEntityNotFoundException(EntityNotFoundException e) {

        ErrorResponseDetails details = new ErrorResponseDetails(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now(),
                List.of()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(details);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDetails> handleIllegalArgumentException(IllegalArgumentException e) {

        ErrorResponseDetails details = new ErrorResponseDetails(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                LocalDateTime.now(),
                List.of()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(details);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDetails> handleRuntimeException(RuntimeException e) {

        ErrorResponseDetails details = new ErrorResponseDetails(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Unexpected error. Try again later",
                LocalDateTime.now(),
                List.of()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(details);
    }
}
