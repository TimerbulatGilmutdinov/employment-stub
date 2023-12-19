package ru.itis.employmentstub.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.employmentstub.model.EmploymentDocument;
import ru.itis.employmentstub.model.UserDB;
import ru.itis.employmentstub.model.Vacancy;
import ru.itis.employmentstub.model.dto.SeekerRespondResponseDto;
import ru.itis.employmentstub.model.enums.EmploymentRole;
import ru.itis.employmentstub.model.enums.Status;
import ru.itis.employmentstub.repository.DocumentRepository;
import ru.itis.employmentstub.repository.UserRepository;
import ru.itis.employmentstub.repository.VacancyRepository;
import ru.itis.employmentstub.util.jwt.AuthorizationHeaderUtil;
import ru.itis.employmentstub.util.jwt.JwtUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class RespondController {
    private final AuthorizationHeaderUtil headerUtil;
    private final UserRepository userRepository;
    private final VacancyRepository vacancyRepository;
    private final DocumentRepository documentRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("/api/v1/vacancy/{id}/respond")
    public ResponseEntity<Long> createVacancyRespond(@PathVariable Long id, HttpServletRequest request) {
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("vacancy with id <" + id + "> not found"));
        String token = headerUtil.getToken(request);
        String email = jwtUtil.parseToken(token).getSub();
        UserDB userDB = userRepository.findByEmail(email);
        var respond = documentRepository.save(EmploymentDocument.builder()
                .responseDate(LocalDate.now())
                .vacancy(vacancy)
                .user(userDB)
                .recipient(EmploymentRole.PERSONNEL_DEPARTMENT)
                .status(Status.IN_REVIEW).build());
        return ResponseEntity.ok(respond.getId());
    }

    @GetMapping("/api/v1/seeker/responds")
    public ResponseEntity<List<SeekerRespondResponseDto>> getAllSeekerResponds(HttpServletRequest request) {
        String token = headerUtil.getToken(request);
        String email = jwtUtil.parseToken(token).getSub();
        UserDB userDB = userRepository.findByEmail(email);
        var respondList = documentRepository.findAllByUser(userDB);
        List<SeekerRespondResponseDto> result = respondList.stream().map(employmentDocument ->
                SeekerRespondResponseDto.builder()
                        .name(employmentDocument.getVacancy().getVacancyName())
                        .respondDate(employmentDocument.getResponseDate())
                        .status(employmentDocument.getStatus()).build()).toList();
        return ResponseEntity.ok(result);
    }
}
