package sgallalucas.fitlab.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sgallalucas.fitlab.model.Student;
import sgallalucas.fitlab.services.StudentService;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody Student student) {
        service.save(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + student.getId().toString()).buildAndExpand().toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Student>> findById(@PathVariable String id) {
        var student = service.findById(UUID.fromString(id));

        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        List<Student> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody Student student) {
        var optional = service.findById(UUID.fromString(id));

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Student s = optional.get();
        s.setName(student.getName());
        s.setBirthDate(student.getBirthDate());
        s.setEmail(student.getEmail());

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
