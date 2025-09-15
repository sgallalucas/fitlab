package sgallalucas.fitlab.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponseDetails handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();

        List<FieldErrorDetails> fieldErrorDetails = fieldErrors.stream().map(
                fe -> new FieldErrorDetails(fe.getField(), fe.getDefaultMessage())).toList();

        return new ErrorResponseDetails(
                HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation error", LocalDateTime.now(), fieldErrorDetails);
    }

    @ExceptionHandler(DuplicateRecordException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDetails handleDuplicateRecordException(DuplicateRecordException e) {
        return new ErrorResponseDetails(HttpStatus.CONFLICT.value(), e.getMessage(), LocalDateTime.now(), List.of());
    }

    @ExceptionHandler(NotAllowedOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDetails handleNotAllowedOperationException(NotAllowedOperationException e) {
        return new ErrorResponseDetails(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now(), List.of());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDetails handelEntityNotFoundException(EntityNotFoundException e) {
        return new ErrorResponseDetails(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now(), List.of());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDetails handleIllegalArgumentException(IllegalArgumentException e) {
        return new ErrorResponseDetails(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now(), List.of());
    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ErrorResponseDetails> handleRuntimeException(RuntimeException e) {
//
//        ErrorResponseDetails details = new ErrorResponseDetails(
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                "Unexpected error. Try again",
//                LocalDateTime.now(),
//                List.of()
//        );
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(details);
//    }
}
