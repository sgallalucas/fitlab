package sgallalucas.fitlab.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sgallalucas.fitlab.exceptions.DuplicateRecordException;
import sgallalucas.fitlab.model.Workout;
import sgallalucas.fitlab.repositories.WorkoutRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkoutValidator {

    private final WorkoutRepository repository;

    public void validation(Workout workout) {
        if (verification(workout)) {
            throw new DuplicateRecordException("This workout already exists");
        }
    }

    public boolean verification(Workout workout) {
        Workout w = repository.findByNameAndTypeAndDescriptionAndProfessorAndStudent(
                workout.getName(),
                workout.getType(),
                workout.getDescription(),
                workout.getProfessor(),
                workout.getStudent());

        if (w == null) {
            return false;
        }
        return true;
    }
}
