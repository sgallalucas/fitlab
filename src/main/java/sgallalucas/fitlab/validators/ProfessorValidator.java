package sgallalucas.fitlab.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sgallalucas.fitlab.exceptions.DuplicateRecordException;
import sgallalucas.fitlab.model.Professor;
import sgallalucas.fitlab.repositories.ProfessorRepository;

@Component
@RequiredArgsConstructor
public class ProfessorValidator {

    private final ProfessorRepository repository;

    public void validation(String email) {
        if (verification(email)) {
            throw new DuplicateRecordException("This email has already been registered");
        }
    }

    public boolean verification(String email) {
        Professor professor = repository.findByEmail(email);

        if (professor == null) {
            return false;
        }
        return true;
    }
}
