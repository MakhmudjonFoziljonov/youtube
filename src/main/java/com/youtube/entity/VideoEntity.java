package com.youtube.entity;

import com.youtube.enums.PlayListStatus;
import com.youtube.enums.VideoType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "videos") // Ensures the table name matches your schema
public class VideoEntity {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "created_date" )
    private LocalDateTime createdDate = LocalDateTime.now(); // Default to now

    @Column(name = "published_date")
    private LocalDateTime publishedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PlayListStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private VideoType type = VideoType.VIDEO;

    @Column(name = "view_count")
    private Long viewCount = 0L;

    @Column(name = "shared_count")
    private Long sharedCount = 0L;

    @Column(name = "like_count")
    private Long likeCount = 0L;

    @Column(name = "dislike_count")
    private Long dislikeCount = 0L;

    @Column(name = "category_id")
    private Integer categoryId;

    // Uncomment and fix relationships if required
    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "category_id", insertable = false, updatable = false)
    // private CategoryEntity category;

     @Column(name = "channel_id", nullable = false)
     private String channelId;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "channel_id", insertable = false, updatable = false)
     private ChannelEntity channel;

    // @Column(name = "attach_id")
    // private String attachId;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "attach_id", insertable = false, updatable = false)
    // private AttachEntity attach;

    // @Column(name = "preview_attach_id", nullable = false)
    // private String previewAttachId;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "preview_attach_id")
    // private AttachEntity previewAttach;

}
