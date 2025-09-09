package sgallalucas.fitlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sgallalucas.fitlab.model.Professor;

import java.util.UUID;

public interface ProfessorRepository extends JpaRepository<Professor, UUID> {

    Professor findByEmail(String email);
}
