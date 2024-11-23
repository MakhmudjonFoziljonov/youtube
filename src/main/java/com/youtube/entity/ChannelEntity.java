package com.youtube.entity;

import com.youtube.enums.ChannelStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "channels")
public class ChannelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Enumerated(EnumType.STRING)
    private ChannelStatus status;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "profile_id")
    private String profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "photo_id")
    private String photoId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", insertable = false, updatable = false)
    private AttachEntity attach;

    @Column(name = "banner_id")
    private Integer bannerID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banner_id", insertable = false, updatable = false)
    private BannerEntity banner;

}
