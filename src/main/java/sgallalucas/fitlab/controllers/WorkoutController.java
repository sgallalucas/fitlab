package sgallalucas.fitlab.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sgallalucas.fitlab.dtos.WorkoutDto;
import sgallalucas.fitlab.model.Professor;
import sgallalucas.fitlab.model.Student;
import sgallalucas.fitlab.model.Workout;
import sgallalucas.fitlab.services.ProfessorService;
import sgallalucas.fitlab.services.StudentService;
import sgallalucas.fitlab.services.WorkoutService;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService service;
    private final ProfessorService professorService;
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Workout> save(@RequestBody WorkoutDto dto) {

        Professor professor = professorService.findById(UUID.fromString(dto.professorId()))
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        Student student = studentService.findById(UUID.fromString(dto.studentId()))
                .orElseThrow(() -> new RuntimeException("Student not found"));

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
}
