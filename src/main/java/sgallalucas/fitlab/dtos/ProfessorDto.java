package sgallalucas.fitlab.dtos;

import sgallalucas.fitlab.enums.Genre;
import sgallalucas.fitlab.enums.Specialization;

import java.time.LocalDate;
import java.util.UUID;

public record ProfessorDto(
        UUID id,
        String name,
        LocalDate birthDate,
        String email,
        Genre genre,
        Specialization specialization
) {
}
