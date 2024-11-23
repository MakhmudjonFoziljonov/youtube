package com.youtube.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ChannelResponseDTO {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}
