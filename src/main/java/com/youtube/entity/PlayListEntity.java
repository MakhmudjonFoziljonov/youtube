package com.youtube.entity;

import com.youtube.enums.PlayListStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@Entity
@Table(name = "play_lists")
public class PlayListEntity {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "descprition", columnDefinition = "text")
    private String description;

    @Enumerated(EnumType.STRING)
    private PlayListStatus playListStatus;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "channel_id", nullable = false)
    private String channelId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id",insertable = false,updatable = false)
    private ChannelEntity channel;


}
