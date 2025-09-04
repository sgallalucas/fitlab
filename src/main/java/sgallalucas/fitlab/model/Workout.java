package sgallalucas.fitlab.model;

import jakarta.persistence.*;
import lombok.Data;
import sgallalucas.fitlab.enums.WorkoutType;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "workout")
@Data
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private WorkoutType type;
    private String description;
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
