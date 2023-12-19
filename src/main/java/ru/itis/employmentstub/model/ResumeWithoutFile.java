package ru.itis.employmentstub.model;

import jakarta.persistence.*;
import lombok.*;
import ru.itis.employmentstub.model.enums.Sex;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ResumeWithoutFile {
    private Long id;
    protected String firstname;
    protected String lastname;
    protected String middlename;
    protected String email;
    protected String phoneNumber;
    protected Sex sex;
    protected String citizenship;
    protected LocalDate birthday;
    private Long resumeFileId;
}
