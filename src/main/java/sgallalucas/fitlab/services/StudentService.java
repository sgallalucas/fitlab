package sgallalucas.fitlab.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgallalucas.fitlab.model.Student;
import sgallalucas.fitlab.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    public Student save(Student student) {
        return repository.save(student);
    }

    public Optional<Student> findById(UUID uuid) {
        return repository.findById(uuid);
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public void update(Student student) {
        repository.save(student);
    }

    public void delete(Student student) {
        repository.delete(student);
    }
}
