package com.youtube.entity;

import com.youtube.enums.ChannelStatus;
import com.youtube.enums.PlayListStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Stack;

@Entity
@Data
@Table(name = "playlists")
public class Playlist {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Enumerated(EnumType.STRING)
    private PlayListStatus status;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "order_number")
    private Long orderNumber;

    @Column(name = "channel_id")
    private String channelId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", insertable = false, updatable = false)
    private ChannelEntity channel;
}
