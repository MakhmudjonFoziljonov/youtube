package com.youtube.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtDTO {
    private String email;
    private String role;
}
