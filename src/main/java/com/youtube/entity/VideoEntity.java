package com.youtube.entity;

import com.youtube.enums.PlayListStatus;
import com.youtube.enums.VideoType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "videos")
public class VideoEntity {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "published_date")
    private LocalDateTime publishedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PlayListStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private VideoType type = VideoType.VIDEO;

    @Column(name = "view_count", nullable = false)
    private Long viewCount = 0L;

    @Column(name = "shared_count", nullable = false)
    private Long sharedCount = 0L;

    @Column(name = "like_count", nullable = false)
    private Long likeCount = 0L;

    @Column(name = "dislike_count", nullable = false)
    private Long dislikeCount = 0L;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",insertable = false, updatable = false)
    private CategoryEntity category;

    @Column(name = "channel_id", nullable = false)
    private String channelId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id",insertable = false, updatable = false)
    private ChannelEntity channel;

    @Column(name = "attach_id")
    private String attachId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id", insertable = false, updatable = false)
    private AttachEntity attach;

  /*  @Column(name = "preview_attach_id", nullable = false)
    private String previewAttachId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preview_attach_id")
    private AttachEntity previewAttach;*/

}
