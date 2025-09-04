package sgallalucas.fitlab.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sgallalucas.fitlab.dtos.ProfessorDto;
import sgallalucas.fitlab.model.Professor;
import sgallalucas.fitlab.services.ProfessorService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/professors")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService service;

    @PostMapping
    public ResponseEntity<ProfessorDto> save(@RequestBody ProfessorDto dto) {
        Professor professor = service.convertToEntity(dto);
        service.save(professor);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + professor.getId().toString())
                .buildAndExpand()
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDto> findById(@PathVariable String id) {
        Professor professor = service.findById(UUID.fromString(id));

        ProfessorDto dto = service.convertToDto(professor);

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDto>> findAll() {
        List<Professor> list = service.findAll();
        List<ProfessorDto> dtoList = new ArrayList<>();

        for (Professor p : list) {
            ProfessorDto dto = service.convertToDto(p);
            dtoList.add((dto));
        }

        return ResponseEntity.ok().body(dtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody ProfessorDto dto){
        Professor professor = service.findById(UUID.fromString(id));

        Professor p = service.convertToEntity(dto);

        professor.setName(p.getName());
        professor.setBirthDate(p.getBirthDate());
        professor.setEmail(p.getEmail());
        professor.setGenre(p.getGenre());
        professor.setSpecialization(p.getSpecialization());

        service.update(professor);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        Professor professor = service.findById(UUID.fromString(id));

        service.delete(professor);

        return ResponseEntity.noContent().build();
    }
}
