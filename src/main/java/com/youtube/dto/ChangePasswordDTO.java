package com.youtube.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDTO {

    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;

}
