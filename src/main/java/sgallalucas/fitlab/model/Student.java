package sgallalucas.fitlab.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import sgallalucas.fitlab.enums.Genre;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "student")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private LocalDate birthDate;
    private String email;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @CreatedDate
    private Instant registrationDate;
    @LastModifiedDate
    private Instant updateDate;

    @OneToMany(mappedBy = "student")
    private List<Workout> workouts;
}
