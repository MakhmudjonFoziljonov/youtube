package com.youtube.dto;

import com.youtube.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDTO {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private ProfileRole role;
}
