package com.youtube.controller;

import com.youtube.entity.EmailHistoryEntity;
import com.youtube.service.EmailHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api")
public class EmailHistoryController {
    private final EmailHistoryService emailHistoryService;

    public EmailHistoryController(EmailHistoryService emailHistoryService) {
        this.emailHistoryService = emailHistoryService;
    }
    @GetMapping
    public ResponseEntity<Page<EmailHistoryEntity>> getAllEmailHistory(Pageable pageable) {
        return ResponseEntity.ok(emailHistoryService.getAllEmailHistory(pageable));
    }

    @GetMapping("/by-email")
    public ResponseEntity<Page<EmailHistoryEntity>> getEmailHistoryByEmail(
            @RequestParam String email, Pageable pageable) {
        return ResponseEntity.ok(emailHistoryService.getEmailHistoryByEmail(email, pageable));
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<EmailHistoryEntity>> filterEmailHistory(
            @RequestParam String email,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate,
            Pageable pageable) {
        return ResponseEntity.ok(emailHistoryService.filterEmailHistory(email, startDate, endDate, pageable));
    }
}
