package ru.itis.employmentstub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.employmentstub.model.BaseUserData;
import ru.itis.employmentstub.model.Resume;
import ru.itis.employmentstub.model.ResumeFile;

import ru.itis.employmentstub.model.ResumeWithoutFile;
import ru.itis.employmentstub.model.dto.AddResumeRequestDto;
import ru.itis.employmentstub.repository.ResumeRepository;

import java.io.IOException;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeRepository resumeRepository;

    @PostMapping(value = "/api/v1/resume", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> addResume(@ModelAttribute AddResumeRequestDto resumeRequestDto) throws IOException {
        var resume = Resume.builder()
                .firstname(resumeRequestDto.getFirstname())
                .lastname(resumeRequestDto.getLastname())
                .middlename(resumeRequestDto.getMiddlename())
                .email(resumeRequestDto.getEmail())
                .phoneNumber(resumeRequestDto.getPhoneNumber())
                .sex(resumeRequestDto.getSex())
                .citizenship(resumeRequestDto.getCitizenship())
                .birthday(resumeRequestDto.getBirthday())
                .resumeFile(ResumeFile.builder()
                        .bytes(resumeRequestDto.getResumeFile().getBytes())
                        .contentType(resumeRequestDto.getResumeFile().getContentType())
                        .name(resumeRequestDto.getResumeFile().getOriginalFilename())
                        .build()).build();
        var r = resumeRepository.save(resume);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/v1/resume/{id}")
    public ResponseEntity<Integer> deleteResume(@PathVariable Long id) {
        resumeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/v1/resume/{id}")
    public ResponseEntity<ResumeWithoutFile> getResume(@PathVariable Long id) {
        var res = resumeRepository.getResumeWithoutFile(id).orElseThrow(() -> new NoSuchElementException("Resume with id <" + id + "> not found"));
        return ResponseEntity.ok(ResumeWithoutFile.builder()
                .id(res.getId())
                .userData(BaseUserData.builder()
                        .firstname(res.getFirstname())
                        .lastname(res.getLastname())
                        .middlename(res.getMiddlename())
                        .email(res.getEmail())
                        .phone(res.getPhoneNumber())
                        .sex(res.getSex())
                        .citizenship(res.getCitizenship())
                        .birthday(res.getBirthday())
                        .build())
                .build());
    }
}
