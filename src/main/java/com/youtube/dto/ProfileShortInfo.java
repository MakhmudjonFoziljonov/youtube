package com.youtube.dto;

import lombok.Data;

@Data
public class ProfileShortInfo {
    private String id;
    private String name;
    private String surname;

    private PhotoShortInfo photo;
}
