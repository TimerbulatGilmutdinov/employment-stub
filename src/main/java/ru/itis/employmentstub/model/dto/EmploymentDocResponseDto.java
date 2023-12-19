package ru.itis.employmentstub.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.itis.employmentstub.model.enums.EmploymentRole;
import ru.itis.employmentstub.model.enums.Status;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class EmploymentDocResponseDto {
    private Long id;
    private LocalDate responseDate;
    private String vacancyName;
    private Status status;
    private EmploymentRole recipient;
}
