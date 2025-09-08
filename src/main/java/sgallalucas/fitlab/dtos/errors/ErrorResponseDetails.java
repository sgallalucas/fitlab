package sgallalucas.fitlab.dtos.errors;

import java.util.List;

public record ErrorResponseDetails(
        Integer status,
        String message,
        List<FieldErrorDetails> fieldErrors
) {
}
