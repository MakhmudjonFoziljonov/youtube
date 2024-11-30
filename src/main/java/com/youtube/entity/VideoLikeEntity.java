package com.youtube.entity;

import com.youtube.enums.LikeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
@Entity
@Table(name = "video_like")
@Getter
@Setter
public class VideoLikeEntity {
    @Id
    @UuidGenerator
    private String  id;

    @Column(name = "profile_id", nullable = false)
    private String profileId;

    @Column(name = "video_id", nullable = false)
    private String videoId;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Enumerated
    @Column(name = "type", nullable = false)
    private LikeType type;

}
