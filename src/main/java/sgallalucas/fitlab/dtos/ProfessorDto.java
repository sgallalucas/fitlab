package sgallalucas.fitlab.dtos;

import jakarta.validation.constraints.*;
import sgallalucas.fitlab.enums.Genre;
import sgallalucas.fitlab.enums.Specialization;

import java.time.LocalDate;
import java.util.UUID;

public record ProfessorDto(
        UUID id,

        @NotBlank(message = "Required field")
        @Size(min = 3, message = "This field must have at least 3 characters")
        String name,

        @NotNull(message = "Required field")
        @Past(message = "Fill in a past date")
        LocalDate birthDate,

        @NotBlank(message = "Required field")
        @Email(message = "Fill in a valid email")
        String email,

        @NotNull(message = "Required field")
        Genre genre,

        @NotNull(message = "Required field")
        Specialization specialization
) {
}
