package sgallalucas.fitlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sgallalucas.fitlab.model.Student;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {

    Student findByEmail(String email);
}
