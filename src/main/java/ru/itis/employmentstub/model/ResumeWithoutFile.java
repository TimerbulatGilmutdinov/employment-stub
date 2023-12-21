package ru.itis.employmentstub.model;

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
    private BaseUserData userData;
}
