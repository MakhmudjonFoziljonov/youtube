package com.youtube.profile.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table
public class AttachEntity {
    @Id
    private String id;

    @Column(nullable = false)
    private String originName;

    @Column(nullable = false)
    private Long size;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String path;

    private Double duration;
    @Column(nullable = false)
    private LocalDateTime createdDate;
}
