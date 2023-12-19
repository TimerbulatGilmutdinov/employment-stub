package ru.itis.employmentstub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.employmentstub.model.ResumeFile;

public interface ResumeFileRepository extends JpaRepository<ResumeFile,Long> {

}
