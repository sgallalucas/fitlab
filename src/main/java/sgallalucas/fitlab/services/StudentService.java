package sgallalucas.fitlab.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgallalucas.fitlab.model.Student;
import sgallalucas.fitlab.repositories.StudentRepository;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    public Student save(Student student) {
        return repository.save(student);
    }
}
