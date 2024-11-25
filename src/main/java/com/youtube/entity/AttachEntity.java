package com.youtube.entity;

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

    @Column(name = "origin_name", nullable = false)
    private String originName;

    @Column(nullable = false)
    private Long size;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String path;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "visible", nullable = false)
    private Boolean visible = true;

    @Column(nullable = false)
    private Double duration;
    @Column(name = "file_data", columnDefinition = "LONGBLOB")
    private byte[] fileData;
}
