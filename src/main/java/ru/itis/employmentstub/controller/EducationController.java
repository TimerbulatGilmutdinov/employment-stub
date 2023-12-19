package ru.itis.employmentstub.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.employmentstub.model.Education;
import ru.itis.employmentstub.model.UserDB;
import ru.itis.employmentstub.repository.EducationRepository;
import ru.itis.employmentstub.repository.UserRepository;
import ru.itis.employmentstub.util.jwt.AuthorizationHeaderUtil;
import ru.itis.employmentstub.util.jwt.JwtUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EducationController {
    private final EducationRepository educationRepository;
    private final AuthorizationHeaderUtil headerUtil;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @GetMapping("/api/v1/users/me/education")
    public ResponseEntity<List<Education>> getUserEducation(HttpServletRequest request) {
        String token = headerUtil.getToken(request);
        String email = jwtUtil.parseToken(token).getSub();
        UserDB userDB = userRepository.findByEmail(email);
        return ResponseEntity.ok(educationRepository.findAllByUserData(userDB));
    }

    @PostMapping("/api/v1/users/me/education")
    public ResponseEntity<Education> createUserEducation(HttpServletRequest request, @RequestBody Education education) {
        String token = headerUtil.getToken(request);
        String email = jwtUtil.parseToken(token).getSub();
        UserDB userDB = userRepository.findByEmail(email);
        education.setUserData(userDB);
        return ResponseEntity.ok(educationRepository.save(education));
    }
}
