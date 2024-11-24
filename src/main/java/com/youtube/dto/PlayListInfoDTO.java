package com.youtube.dto;

import com.youtube.entity.ProfileEntity;
import com.youtube.enums.PlayListStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayListInfoDTO {
    private String id;
    private String name;
    private String description;
    private PlayListStatus status;
    private Integer orderNum;

    private String channelId;
    private String profileId;
}
