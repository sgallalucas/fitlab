package sgallalucas.fitlab.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgallalucas.fitlab.dtos.StudentDto;
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

    public Student convertToEntity(StudentDto dto) {
        Student student = new Student();
        student.setId(dto.id());
        student.setName(dto.name());
        student.setBirthDate(dto.birthDate());
        student.setEmail((dto.email()));

        return student;
    }

    public StudentDto convertToDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getBirthDate(),
                student.getEmail());
    }
}
