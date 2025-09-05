package sgallalucas.fitlab.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sgallalucas.fitlab.dtos.WorkoutDto;
import sgallalucas.fitlab.model.Professor;
import sgallalucas.fitlab.model.Student;
import sgallalucas.fitlab.model.Workout;
import sgallalucas.fitlab.services.ProfessorService;
import sgallalucas.fitlab.services.StudentService;
import sgallalucas.fitlab.services.WorkoutService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService service;
    private final ProfessorService professorService;
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Workout> save(@RequestBody @Valid WorkoutDto dto) {

        Professor professor = professorService.findById(UUID.fromString(dto.professorId()));
        Student student = studentService.findById(UUID.fromString(dto.studentId()));

        Workout workout = new Workout();

        workout.setName(dto.name());
        workout.setType(dto.type());
        workout.setDescription(dto.description());
        workout.setProfessor(professor);
        workout.setStudent(student);

        service.save(workout);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + workout.getId().toString())
                .buildAndExpand()
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutDto> findById(@PathVariable String id) {
        Workout workout = service.findById(UUID.fromString(id));

        WorkoutDto dto = service.convertToDto(workout);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<WorkoutDto>> findAll() {
        List<Workout> list = service.findAll();
        List<WorkoutDto> dtoList = new ArrayList<>();

        for (Workout w : list) {
            WorkoutDto dto = service.convertToDto(w);
            dtoList.add(dto);
        }

        return ResponseEntity.ok().body(dtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody @Valid WorkoutDto dto) {
        Workout workout = service.findById(UUID.fromString(id));

        Professor professor = professorService.findById(UUID.fromString(dto.professorId()));
        Student student = studentService.findById(UUID.fromString(dto.studentId()));

        workout.setName(dto.name());
        workout.setType(dto.type());
        workout.setDescription(dto.description());
        workout.setProfessor(professor);
        workout.setStudent(student);

        service.update(workout);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        Workout workout = service.findById(UUID.fromString(id));

        service.delete(workout);

        return ResponseEntity.noContent().build();
    }
}
