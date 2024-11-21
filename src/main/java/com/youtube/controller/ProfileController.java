package com.youtube.controller;

import com.youtube.config.AppConfig;
import com.youtube.dto.AuthRequestDTO;
import com.youtube.dto.AuthResponseDTO;
import com.youtube.dto.ProfileDTO;
import com.youtube.enums.AppLang;
import com.youtube.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/registration")
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileDTO dto) {
        ProfileDTO result = profileService.registration(dto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/authorization")
    public ResponseEntity<AuthResponseDTO> authorization(@RequestBody AuthRequestDTO dto,
                                                         @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLang lang) {
        AuthResponseDTO result = profileService.authorization(dto,lang);
        return ResponseEntity.ok(result);
    }
}


