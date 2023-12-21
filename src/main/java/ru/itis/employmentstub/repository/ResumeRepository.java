package ru.itis.employmentstub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.employmentstub.model.Resume;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    @Query(value = "select r.id,r.birthday,r.citizenship,r.email,r.firstname,r.lastname,r.middlename,r.phone,r.sex,r.resume_file_id from resume r where r.id=:id", nativeQuery = true)
    Optional<Resume> getResumeWithoutFile(@Param("id") Long id);
}
