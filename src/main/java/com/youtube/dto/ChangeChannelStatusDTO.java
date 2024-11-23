package com.youtube.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeChannelStatusDTO {
    private Long channelId;
    private Boolean isActive;
}
