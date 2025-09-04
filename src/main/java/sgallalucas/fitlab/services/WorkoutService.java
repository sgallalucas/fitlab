package sgallalucas.fitlab.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgallalucas.fitlab.model.Workout;
import sgallalucas.fitlab.repositories.WorkoutRepository;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository repository;

    public Workout save(Workout workout) {
        return repository.save(workout);
    }
}
