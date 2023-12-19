package ru.itis.employmentstub.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer educationLevelId;

    @JsonProperty(required = true)
    public Integer countyId;

    @JsonProperty("nameOfInstitute")
    private String instituteName;

    @JsonProperty(required = true)
    private String graduateYear;

    @JsonProperty(required = true)
    private String specialization;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserDB userData;
}
