package sgallalucas.fitlab.dtos.errors;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponseDetails(
        Integer status,
        String message,
        LocalDateTime timestamp,
        List<FieldErrorDetails> fieldErrors
) {
}
