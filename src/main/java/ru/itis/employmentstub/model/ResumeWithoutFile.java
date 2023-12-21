package ru.itis.employmentstub.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ResumeWithoutFile {
    private Long id;
    private BaseUserData userData;
}
