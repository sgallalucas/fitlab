package sgallalucas.fitlab.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgallalucas.fitlab.dtos.WorkoutDto;
import sgallalucas.fitlab.model.Workout;
import sgallalucas.fitlab.repositories.WorkoutRepository;
import sgallalucas.fitlab.validators.WorkoutValidator;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository repository;
    private final WorkoutValidator validator;

    public Workout save(Workout workout) {
        validator.validation(workout);
        return repository.save(workout);
    }

    public Workout findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Workout not found"));
    }

    public List<Workout> findAll() {
        return repository.findAll();
    }

    public void update(Workout workout) {
        validator.validation(workout);
        repository.save(workout);
    }

    public void delete(Workout workout) {
        repository.delete(workout);
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
