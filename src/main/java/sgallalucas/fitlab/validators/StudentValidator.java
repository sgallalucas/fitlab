package sgallalucas.fitlab.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sgallalucas.fitlab.exceptions.DuplicateRecordException;
import sgallalucas.fitlab.model.Student;
import sgallalucas.fitlab.repositories.StudentRepository;

@Component
@RequiredArgsConstructor
public class StudentValidator {

    private final StudentRepository repository;

    public void validation(String email) {
        if (verification(email)) {
            throw new DuplicateRecordException("This email has already been registered");
        }
    }

    public boolean verification(String email) {
        Student student = repository.findByEmail(email);

        if (student == null) {
            return false;
        }
        return true;
    }
}
