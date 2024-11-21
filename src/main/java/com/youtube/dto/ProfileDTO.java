package com.youtube.dto;

import com.youtube.enums.ProfileRole;
import com.youtube.enums.ProfileStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProfileDTO {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private ProfileStatus status;
    private ProfileRole role;
    private String photoId;
    private LocalDateTime createdDate;
    private String jwtToken;
//    private AttachDTO photo;

    private List<String> imageIds;
}
