package com.youtube.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelDTO {
    @NotNull
    private String name;
    private String description;
    private String photo;
    private Integer bannerId;
    private String profileId;
}
