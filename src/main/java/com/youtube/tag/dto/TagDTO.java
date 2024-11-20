package com.youtube.tag.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TagDTO {

    private int id;
    private String name;
    private LocalDateTime createdate;
}
