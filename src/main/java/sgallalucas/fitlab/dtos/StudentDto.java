package sgallalucas.fitlab.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import sgallalucas.fitlab.enums.Genre;

import java.time.LocalDate;
import java.util.UUID;

public record StudentDto(
        UUID id,

        @NotBlank(message = "Required field")
        String name,

        @NotNull(message = "Required field")
        @Past(message = "Fill in a past date")
        LocalDate birthDate,

        @NotBlank(message = "Required field")
        @Email(message = "Fill in a valid email")
        String email,

        @NotNull(message = "Required field")
        Genre genre
) {
}
