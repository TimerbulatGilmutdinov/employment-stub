package ru.itis.employmentstub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.employmentstub.model.EmploymentDocument;
import ru.itis.employmentstub.model.UserDB;
import ru.itis.employmentstub.model.Vacancy;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<EmploymentDocument,Long> {
    List<EmploymentDocument> findAllByUser(UserDB userDB);
    List<EmploymentDocument> findAllByVacancy(Vacancy vacancy);
    Optional<EmploymentDocument> findByVacancyAndId(Vacancy vacancy,Long id);
}
