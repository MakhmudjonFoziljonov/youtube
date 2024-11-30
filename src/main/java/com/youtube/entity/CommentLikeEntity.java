package com.youtube.entity;

import com.youtube.enums.LikeType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
@Entity
@Data
@Table(name = "comment_likes")
public class CommentLikeEntity {
    @Id
    @UuidGenerator
    private String  id;

    @Column(name = "profile_id", nullable = false)
    private String profileId;

    @Column(name = "comment_id", nullable = false)
    private String commentId;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private LikeType type;

}
