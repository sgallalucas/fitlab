package sgallalucas.fitlab.dtos;

import sgallalucas.fitlab.enums.WorkoutType;

import java.util.UUID;

public record WorkoutDto(
        UUID id,
        String name,
        WorkoutType type,
        String description,
        String professorId,
        String studentId
) {
}
