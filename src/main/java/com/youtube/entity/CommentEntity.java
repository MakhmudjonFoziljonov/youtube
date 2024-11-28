package com.youtube.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class CommentEntity {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;


    @Column(name = "profile_id")
    private Long profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "video_id")
    private String videoId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    private VideoEntity video;

    @Column(name = "reply_id")
    private String replyId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_id", insertable = false, updatable = false)
    private CommentEntity reply;

}
