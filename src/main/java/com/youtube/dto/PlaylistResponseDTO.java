package com.youtube.dto;

import com.youtube.enums.PlaylistStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistResponseDTO {
    private Long id;
    private Long channelId;
    private String name;
    private String description;
    private PlaylistStatusEnum status;
    private Integer orderNum;
}