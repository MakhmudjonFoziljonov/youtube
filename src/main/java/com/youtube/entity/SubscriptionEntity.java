package com.youtube.entity;

import com.youtube.enums.ChannelStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "subscriptions")
public class SubscriptionEntity {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "profile_id", nullable = false)
    private String profileId;

    @Column(name = "channel_id", nullable = false)
    private String channelId;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate=LocalDateTime.now();

    @Column(name = "unsubscribe_date")
    private LocalDateTime unsubscribeDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ChannelStatus status;
}
