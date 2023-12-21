package ru.itis.employmentstub.model;

import jakarta.persistence.*;
import lombok.*;
import ru.itis.employmentstub.model.enums.Sex;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected String firstname;
    protected String lastname;
    protected String middlename;
    protected String email;
    protected String phone;
    @Enumerated(EnumType.STRING)
    protected Sex sex;
    protected String citizenship;
    protected LocalDate birthday;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resume_file_id", nullable = false)
    private ResumeFile resumeFile;
}
