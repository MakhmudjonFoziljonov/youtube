package com.youtube.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class VideoWatchedDTO {
    private String id;
    private LocalDateTime createdDate;
    private String profileId;
    private String videoId;
}
