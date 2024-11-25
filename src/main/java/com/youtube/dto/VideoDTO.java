package com.youtube.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VideoDTO {
    private String id;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private String attachId; // Fayl ID (URL yoki path)
    private String channelId;
    private Integer CategoryId;
    private Long viewCount;
    private Long likeCount;
    private Long dislikeCount;

}
