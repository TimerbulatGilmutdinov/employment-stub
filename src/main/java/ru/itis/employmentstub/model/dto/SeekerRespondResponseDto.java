package ru.itis.employmentstub.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.itis.employmentstub.model.enums.Status;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeekerRespondResponseDto {
    private String name;
    private LocalDate respondDate;
    private Status status;
}
