package ru.itis.employmentstub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.employmentstub.model.Vacancy;

public interface VacancyRepository extends JpaRepository<Vacancy,Long> {
}
