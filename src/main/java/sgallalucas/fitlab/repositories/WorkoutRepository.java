package sgallalucas.fitlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sgallalucas.fitlab.model.Workout;

import java.util.UUID;

public interface WorkoutRepository extends JpaRepository<Workout, UUID> {
}
