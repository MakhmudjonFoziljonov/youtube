package com.youtube.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class VideoTagDTO {
    private String id;
    private String videoId;
    private String tagId;
    private String tagName;
    private LocalDateTime createdDate;
}
