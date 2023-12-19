package ru.itis.employmentstub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.employmentstub.model.ResumeFile;
import ru.itis.employmentstub.repository.ResumeFileRepository;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class ResumeFileController {
    private final ResumeFileRepository resumeFileRepository;

    @GetMapping("/api/v1/resume/{id}/file")
    public ResponseEntity<ResumeFile> getResumeById(@PathVariable Long id) {
        var file = resumeFileRepository.findById(id).orElseThrow(() -> new NoSuchElementException("resume file with id <" + id + "> not found"));
        return ResponseEntity.ok(file);
    }
}
