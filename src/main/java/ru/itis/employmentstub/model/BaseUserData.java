package ru.itis.employmentstub.model;

import lombok.*;
import ru.itis.employmentstub.model.enums.Sex;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseUserData {
    protected String firstname;
    protected String lastname;
    protected String middlename;
    protected String email;
    protected String phone;
    protected Sex sex;
    protected String citizenship;
    protected LocalDate birthday;
}
