package ru.itis.employmentstub.model;

import jakarta.persistence.*;
import lombok.*;
import ru.itis.employmentstub.model.enums.Sex;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserDB {
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
}
