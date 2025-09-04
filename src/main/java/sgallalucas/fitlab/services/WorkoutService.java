package sgallalucas.fitlab.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgallalucas.fitlab.dtos.WorkoutDto;
import sgallalucas.fitlab.model.Workout;
import sgallalucas.fitlab.repositories.WorkoutRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository repository;

    public Workout save(Workout workout) {
        return repository.save(workout);
    }

    public Workout findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workout not found"));
    }

    public WorkoutDto convertToDto(Workout workout) {
        return new WorkoutDto(
                workout.getId(),
                workout.getName(),
                workout.getType(),
                workout.getDescription(),
                workout.getProfessor().getId().toString(),
                workout.getStudent().getId().toString()
        );
    }
}
