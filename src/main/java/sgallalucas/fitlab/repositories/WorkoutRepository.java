package sgallalucas.fitlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sgallalucas.fitlab.enums.WorkoutType;
import sgallalucas.fitlab.model.Professor;
import sgallalucas.fitlab.model.Student;
import sgallalucas.fitlab.model.Workout;

import java.util.UUID;

public interface WorkoutRepository extends JpaRepository<Workout, UUID> {

    boolean existsByStudent(Student student);

    boolean existsByProfessor(Professor professor);

    Workout findByNameAndTypeAndDescriptionAndProfessorAndStudent(String name, WorkoutType type, String description, Professor professor, Student student);
}
