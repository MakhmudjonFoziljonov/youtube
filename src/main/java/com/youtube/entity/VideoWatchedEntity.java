package com.youtube.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Stack;

@Entity
@Getter
@Setter
@Table(name = "video_watches")
public class VideoWatchedEntity {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "profile_id")
    private String profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;
/*
    @Column(name = "video_id")
    private String videoId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", insertable = false,updatable = false)
    private VideoEntity video;*/
}
