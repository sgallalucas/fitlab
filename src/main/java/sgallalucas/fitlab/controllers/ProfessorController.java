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
        var optional = service.findById(UUID.fromString(id));

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ProfessorDto dto = service.convertToDto(optional.get());

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
        var optional = service.findById(UUID.fromString(id));
        Professor professor = service.convertToEntity(dto);

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Professor p = optional.get();
        p.setName(professor.getName());
        p.setBirthDate(professor.getBirthDate());
        p.setEmail(professor.getEmail());
        p.setGenre(professor.getGenre());
        p.setSpecialization(professor.getSpecialization());

        service.update(p);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        var optional = service.findById(UUID.fromString(id));

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.delete(optional.get());

        return ResponseEntity.noContent().build();
    }
}
