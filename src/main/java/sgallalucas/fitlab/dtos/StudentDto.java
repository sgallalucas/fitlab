package sgallalucas.fitlab.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record StudentDto(

        UUID id,
        String name,
        LocalDate birthDate,
        String email
) {
}
