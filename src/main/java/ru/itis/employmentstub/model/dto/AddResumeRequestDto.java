package ru.itis.employmentstub.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.employmentstub.model.enums.Sex;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AddResumeRequestDto {
    protected String firstname;
    protected String lastname;
    protected String middlename;
    protected String email;
    protected String phone;
    protected Sex sex;
    protected String citizenship;
    protected LocalDate birthday;
    private MultipartFile resumeFile;
}
