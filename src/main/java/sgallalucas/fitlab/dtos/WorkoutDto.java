package sgallalucas.fitlab.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import sgallalucas.fitlab.enums.WorkoutType;

import java.util.UUID;

public record WorkoutDto(
        UUID id,

        @NotBlank(message = "Required field")
        @Size(min = 3, message = "This field must have at least 3 characters")
        String name,

        @NotNull(message = "Required field")
        WorkoutType type,

        @NotBlank(message = "Required field")
        @Size(max = 200)
        String description,

        @NotBlank(message = "Required field")
        @Size(min = 36, message = "Invalid")
        String professorId,

        @NotBlank(message = "Required field")
        @Size(min = 36, message = "Invalid")
        String studentId
) {
}
