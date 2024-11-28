package com.youtube.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelShortInfo {
    private String id;
    private String name;
    private PhotoShortInfo photo;
}