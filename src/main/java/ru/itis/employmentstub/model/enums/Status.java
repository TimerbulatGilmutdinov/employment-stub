package ru.itis.employmentstub.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    IN_REVIEW, INVITATION, REJECTED;

    @JsonValue
    public String getAsJson() {
        return this.name();
    }
}