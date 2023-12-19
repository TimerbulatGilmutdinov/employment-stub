package ru.itis.employmentstub.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EmploymentRole {
    ABIT, SCHOOL, GUEST, SEEKER, ATTEND, STUD, EMPL, ASPIR, ABITUR, SUPERVISOR, PERSONNEL_DEPARTMENT, LABOR_PROTECTION_DEPARTMENT, ACCOUNTING;

    public static String[] getManagementRoles() {
        return new String[]{SUPERVISOR.name(), PERSONNEL_DEPARTMENT.name(), LABOR_PROTECTION_DEPARTMENT.name(), ACCOUNTING.name()};
    }

    @JsonValue
    public String getAsJson() {
        return this.name();
    }
}