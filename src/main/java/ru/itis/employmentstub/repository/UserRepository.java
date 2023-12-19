package ru.itis.employmentstub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.employmentstub.model.UserDB;

public interface UserRepository extends JpaRepository<UserDB,Long> {
    UserDB findByEmail(String email);
}
