package com.youtube.service;

import com.youtube.entity.EmailHistoryEntity;
import com.youtube.repository.EmailHistoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailHistoryService {
    private final EmailHistoryRepository emailHistoryRepository;

    public EmailHistoryService(EmailHistoryRepository emailHistoryRepository) {
        this.emailHistoryRepository = emailHistoryRepository;
    }
    public Page<EmailHistoryEntity> getAllEmailHistory(Pageable pageable) {
        return emailHistoryRepository.findAll(pageable);
    }

    public Page<EmailHistoryEntity> getEmailHistoryByEmail(String email, Pageable pageable) {
        return emailHistoryRepository.findByToEmail(email, pageable);
    }

    public Page<EmailHistoryEntity> filterEmailHistory(String email, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return emailHistoryRepository.findByToEmailAndCreatedDateBetween(email, startDate, endDate, pageable);
    }
}
