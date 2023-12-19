package ru.itis.employmentstub.model.enums;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleWrapper {
    private EmploymentRole recipient;
}
