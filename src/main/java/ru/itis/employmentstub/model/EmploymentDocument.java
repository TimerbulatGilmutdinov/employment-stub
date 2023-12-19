package ru.itis.employmentstub.model;

import jakarta.persistence.*;
import lombok.*;
import ru.itis.employmentstub.model.enums.EmploymentRole;
import ru.itis.employmentstub.model.enums.Status;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class EmploymentDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private EmploymentRole recipient;
    private LocalDate responseDate;
    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDB user;
}
