package com.youtube.dto;

import com.youtube.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponseDTO {
    private String name;
    private String surname;
    private String Email;
    private ProfileRole role;
    private String jwtToken;
}
