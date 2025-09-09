package sgallalucas.fitlab.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgallalucas.fitlab.dtos.ProfessorDto;
import sgallalucas.fitlab.exceptions.NotAllowedOperationException;
import sgallalucas.fitlab.model.Professor;
import sgallalucas.fitlab.repositories.ProfessorRepository;
import sgallalucas.fitlab.repositories.WorkoutRepository;
import sgallalucas.fitlab.validators.ProfessorValidator;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository repository;
    private final ProfessorValidator validator;
    private final WorkoutRepository workoutRepository;

    public Professor save(Professor professor) {
        validator.validation(professor.getEmail());
        return repository.save(professor);
    }

    public Professor findById(UUID uuid) {
        return repository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Professor not found"));
    }

    public List<Professor> findAll() {
        return repository.findAll();
    }

    public void update(Professor professor) {
        validator.validation(professor.getEmail());
        repository.save(professor);
    }

    public void delete(Professor professor) {
        if (hasWorkout(professor)) {
            throw new NotAllowedOperationException("It is not allowed delete a professor who has workouts");
        }
        repository.delete(professor);
    }

    public boolean hasWorkout(Professor professor) {
        return workoutRepository.existsByProfessor(professor);
    }

    public Professor convertToEntity(ProfessorDto dto) {
        Professor professor = new Professor();
        professor.setId(dto.id());
        professor.setName(dto.name());
        professor.setBirthDate(dto.birthDate());
        professor.setEmail(dto.email());
        professor.setGenre(dto.genre());
        professor.setSpecialization(dto.specialization());

        return professor;
    }

    public ProfessorDto convertToDto(Professor professor) {
        return new ProfessorDto(
                professor.getId(),
                professor.getName(),
                professor.getBirthDate(),
                professor.getEmail(),
                professor.getGenre(),
                professor.getSpecialization());
    }
}
