package sgallalucas.fitlab.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sgallalucas.fitlab.dtos.StudentDto;
import sgallalucas.fitlab.model.Student;
import sgallalucas.fitlab.services.StudentService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PostMapping
    public ResponseEntity<StudentDto> save(@RequestBody @Valid StudentDto dto) {
        Student student = service.convertToEntity(dto);
        service.save(student);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + student.getId().toString())
                .buildAndExpand()
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<StudentDto>> findById(@PathVariable String id) {
        Student student = service.findById(UUID.fromString(id));

        StudentDto dto = service.convertToDto(student);

        return ResponseEntity.ok().body(Optional.ofNullable(dto));
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> findAll() {
        List<Student> list = service.findAll();
        List<StudentDto> listDto = new ArrayList<>();

        for (Student s : list) {
            StudentDto dto = service.convertToDto(s);
            listDto.add(dto);
        }

        return ResponseEntity.ok().body(listDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody @Valid StudentDto dto) {
        Student student = service.findById(UUID.fromString(id));

        Student s = service.convertToEntity(dto);

        student.setName(s.getName());
        student.setBirthDate(s.getBirthDate());
        student.setEmail(s.getEmail());
        student.setGenre(s.getGenre());

        service.update(s);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        Student student = service.findById(UUID.fromString(id));

        service.delete(student);

        return ResponseEntity.noContent().build();
    }
}
