package com.youtube.dto;

import com.youtube.entity.AttachEntity;
import lombok.Data;

import java.nio.channels.Channel;
import java.time.LocalDateTime;

@Data
public class VideoShortInfo {
    private String id;
    private String title;
    private AttachEntity attach;
    private LocalDateTime publishedDate;
    private Channel channel;
    private String url;
    private String viewCount;
    private String duration;
}
