package sgallalucas.fitlab.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import sgallalucas.fitlab.enums.Genre;
import sgallalucas.fitlab.enums.Specialization;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "professor")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private LocalDate birthDate;
    private String email;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    @CreatedDate
    private Instant registrationDate;
    @LastModifiedDate
    private Instant updateDate;
    @OneToMany(mappedBy = "professor")
    private List<Workout> workouts = new ArrayList<>();
}
