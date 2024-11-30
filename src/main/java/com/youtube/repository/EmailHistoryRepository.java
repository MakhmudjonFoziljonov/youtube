package com.youtube.repository;

import com.youtube.entity.EmailHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface EmailHistoryRepository extends CrudRepository<EmailHistoryEntity,String> {

    Page<EmailHistoryEntity> findByToEmail(String toEmail, Pageable pageable);

    Page<EmailHistoryEntity> findByToEmailAndCreatedDateBetween(
            String toEmail,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    );

    Page<EmailHistoryEntity> findAll(Pageable pageable);

}
