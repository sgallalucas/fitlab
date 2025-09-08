package sgallalucas.fitlab.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgallalucas.fitlab.dtos.StudentDto;
import sgallalucas.fitlab.exceptions.NotAllowedOperationException;
import sgallalucas.fitlab.model.Student;
import sgallalucas.fitlab.repositories.StudentRepository;
import sgallalucas.fitlab.repositories.WorkoutRepository;
import sgallalucas.fitlab.validators.StudentValidator;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final WorkoutRepository workoutRepository;
    private final StudentValidator validator;

    public Student save(Student student) {
        validator.validation(student.getEmail());
        return repository.save(student);
    }

    public Student findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public void update(Student student) {
        validator.validation(student.getEmail());
        repository.save(student);
    }

    public void delete(Student student) {
        if (hasWorkout(student)) {
            throw new NotAllowedOperationException("It is not allowed delete a student who has workouts");
        }
        repository.delete(student);
    }

    public boolean hasWorkout(Student student) {
        return workoutRepository.existsByStudent(student);
    }

    public Student convertToEntity(StudentDto dto) {
        Student student = new Student();
        student.setId(dto.id());
        student.setName(dto.name());
        student.setBirthDate(dto.birthDate());
        student.setEmail((dto.email()));
        student.setGenre(dto.genre());
        return student;
    }

    public StudentDto convertToDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getBirthDate(),
                student.getEmail(),
                student.getGenre());
    }
}
