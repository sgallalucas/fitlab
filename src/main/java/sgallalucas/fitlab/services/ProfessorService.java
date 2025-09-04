package sgallalucas.fitlab.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sgallalucas.fitlab.dtos.ProfessorDto;
import sgallalucas.fitlab.model.Professor;
import sgallalucas.fitlab.repositories.ProfessorRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository repository;

    public Professor save(Professor professor) {
        return repository.save(professor);
    }

    public Professor findById(UUID uuid) {
        return repository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Professor not found"));
    }

    public List<Professor> findAll() {
        return repository.findAll();
    }

    public void update(Professor professor) {
        repository.save(professor);
    }

    public void delete(Professor professor) {
        repository.delete(professor);
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
