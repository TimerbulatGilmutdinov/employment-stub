package ru.itis.employmentstub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.employmentstub.model.Education;
import ru.itis.employmentstub.model.UserDB;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education,Long> {
    List<Education> findAllByUserDB(UserDB userDB);
}
