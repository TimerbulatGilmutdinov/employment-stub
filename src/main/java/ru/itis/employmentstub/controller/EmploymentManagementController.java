package ru.itis.employmentstub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.employmentstub.model.UserDB;
import ru.itis.employmentstub.model.Vacancy;
import ru.itis.employmentstub.model.dto.EmploymentDocResponseDto;
import ru.itis.employmentstub.model.enums.RoleWrapper;
import ru.itis.employmentstub.model.enums.StatusWrapper;
import ru.itis.employmentstub.repository.DocumentRepository;
import ru.itis.employmentstub.repository.UserRepository;
import ru.itis.employmentstub.repository.VacancyRepository;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class EmploymentManagementController {
    private final VacancyRepository vacancyRepository;
    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;

    @PostMapping("/api/v1/register")
    public ResponseEntity<UserDB> createUser(@RequestBody UserDB userDB) {
        return ResponseEntity.ok(userRepository.save(userDB));
    }

    @PostMapping("/api/v1/vacancy/create")
    public ResponseEntity<Vacancy> createVacancy(@RequestBody Vacancy vacancy) {
        return ResponseEntity.ok(vacancyRepository.save(vacancy));
    }

    @GetMapping("/api/v1/vacancy/{id}")
    public ResponseEntity<Vacancy> getVacancy(@PathVariable Long id) {
        var vacancy = vacancyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("vacancy with id <" + id + "> not found"));
        return ResponseEntity.ok(vacancy);
    }


    @GetMapping("/api/v1/vacancy/{vacancyId}/responds/{respondId}")
    public ResponseEntity<EmploymentDocResponseDto> getVacancyRespond(@PathVariable("vacancyId") Long vacancyId, @PathVariable("respondId") Long respondId) {
        var vacancy = vacancyRepository.findById(vacancyId).orElseThrow(() -> new NoSuchElementException("vacancy with id <" + vacancyId + "> not found"));
        var respond = documentRepository.findByVacancyAndId(vacancy, respondId).orElseThrow(() -> new NoSuchElementException("respond with id <" + respondId + "> not found"));
        return ResponseEntity.ok(EmploymentDocResponseDto.builder()
                .id(respond.getId())
                .vacancyName(respond.getVacancy().getVacancyName())
                .status(respond.getStatus())
                .responseDate(respond.getResponseDate())
                .recipient(respond.getRecipient())
                .build());
    }

    @DeleteMapping("/api/v1/vacancy/{vacancyId}/responds/{respondId}")
    public ResponseEntity<Void> deleteVacancyRespond(@PathVariable("vacancyId") Long vacancyId, @PathVariable("respondId") Long respondId) {
        documentRepository.deleteById(respondId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/v1/vacancy/{vacancyId}/responds/{respondId}/status")
    public ResponseEntity<EmploymentDocResponseDto> changeRespondStatus(@PathVariable("vacancyId") Long vacancyId, @PathVariable("respondId") Long respondId, @RequestBody StatusWrapper status) {
        var vacancy = vacancyRepository.findById(vacancyId).orElseThrow(() -> new NoSuchElementException("vacancy with id <" + vacancyId + "> not found"));
        var respond = documentRepository.findByVacancyAndId(vacancy, respondId).orElseThrow(() -> new NoSuchElementException("respond with id <" + respondId + "> not found"));
        respond.setStatus(status.getStatus());
        documentRepository.save(respond);
        return ResponseEntity.ok(EmploymentDocResponseDto.builder()
                .id(respond.getId())
                .vacancyName(respond.getVacancy().getVacancyName())
                .status(respond.getStatus())
                .responseDate(respond.getResponseDate())
                .recipient(respond.getRecipient())
                .build());
    }

    @PutMapping("/api/v1/vacancy/{vacancyId}/responds/{respondId}/recipient")
    public ResponseEntity<EmploymentDocResponseDto> changeRespondRecipient(@PathVariable("vacancyId") Long vacancyId, @PathVariable("respondId") Long respondId, @RequestBody RoleWrapper recipient) {
        var vacancy = vacancyRepository.findById(vacancyId).orElseThrow(() -> new NoSuchElementException("vacancy with id <" + vacancyId + "> not found"));
        var respond = documentRepository.findByVacancyAndId(vacancy, respondId).orElseThrow(() -> new NoSuchElementException("respond with id <" + respondId + "> not found"));
        respond.setRecipient(recipient.getRecipient());
        documentRepository.save(respond);
        return ResponseEntity.ok(EmploymentDocResponseDto.builder()
                .id(respond.getId())
                .vacancyName(respond.getVacancy().getVacancyName())
                .status(respond.getStatus())
                .responseDate(respond.getResponseDate())
                .recipient(respond.getRecipient())
                .build());
    }

    @GetMapping("/api/v1/vacancy/{vacancyId}/responds")
    public ResponseEntity<List<EmploymentDocResponseDto>> getAllRespondsByVacancy(@PathVariable("vacancyId") Long vacancyId) {
        var vacancy = vacancyRepository.findById(vacancyId).orElseThrow(() -> new NoSuchElementException("vacancy with id <" + vacancyId + "> not found"));
        var respondList = documentRepository.findAllByVacancy(vacancy);
        var result = respondList.stream().map(respond -> EmploymentDocResponseDto.builder()
                .id(respond.getId())
                .vacancyName(respond.getVacancy().getVacancyName())
                .status(respond.getStatus())
                .responseDate(respond.getResponseDate())
                .recipient(respond.getRecipient())
                .build()).toList();
        return ResponseEntity.ok(result);
    }
}
