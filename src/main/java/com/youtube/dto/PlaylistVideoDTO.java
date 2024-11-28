package com.youtube.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PlaylistVideoDTO {
    private Integer id;
    private String playlistId;
    private String videoId;
    private LocalDateTime createdDate;
    private Integer orderNum;
}
