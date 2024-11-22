package com.youtube.entity;

import com.youtube.enums.ChannelStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "channel")
public class ChannelEntity {
    @Id
    @GeneratedValue
    private String id;
    @Column(name = "name")
    private String name;

    @Column(name = "photo_id")
    private String photoId;

    @Column(name = "description")
    private String description;

    @Column(name= "banner_id")
    private String bannerID;

    @Enumerated(EnumType.STRING)
    private ChannelStatus status;
    @Column(name = "profile_id")
    private String profile_id;



}
