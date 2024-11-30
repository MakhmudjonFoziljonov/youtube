package com.youtube.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class SubscriptionInfo {
    private String id;
    private ChannelDTO channel;
    private LocalDateTime createdDate;
}
