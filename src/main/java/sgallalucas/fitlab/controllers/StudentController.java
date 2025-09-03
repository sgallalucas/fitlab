package sgallalucas.fitlab.controllers;

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
    public ResponseEntity<StudentDto> save(@RequestBody StudentDto dto) {
        Student student = service.convertToEntity(dto);
        service.save(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + student.getId().toString()).buildAndExpand().toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<StudentDto>> findById(@PathVariable String id) {
        var student = service.findById(UUID.fromString(id));

        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        StudentDto dto = service.convertToDto(student.get());

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
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody StudentDto dto) {
        var optional = service.findById(UUID.fromString(id));
        Student student = service.convertToEntity(dto);

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Student s = optional.get();
        s.setName(student.getName());
        s.setBirthDate(student.getBirthDate());
        s.setEmail(student.getEmail());
        s.setGenre(student.getGenre());

        service.update(s);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        var student = service.findById(UUID.fromString(id));

        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Student s = student.get();
        service.delete(s);

        return ResponseEntity.noContent().build();
    }
}
