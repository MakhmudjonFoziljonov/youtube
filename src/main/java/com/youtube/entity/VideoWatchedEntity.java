package com.youtube.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "videos_watched")
public class VideoWatchedEntity {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "created_date")
    private LocalDateTime createdDate;


    @Column(name = "profile_id")
    private String profile_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "video_id")
    private String videoId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    private VideoEntity video;


}
