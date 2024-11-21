package com.youtube.controller;

import com.youtube.dto.AuthRequestDTO;
import com.youtube.dto.AuthResponseDTO;
import com.youtube.dto.ChangePasswordDTO;
import com.youtube.dto.ProfileDTO;
import com.youtube.enums.AppLang;
import com.youtube.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        AuthResponseDTO result = profileService.authorization(dto, lang);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody @Valid ChangePasswordDTO changePasswordDTO) {
        boolean isChanged = profileService.changePassword(changePasswordDTO.getOldPassword(), changePasswordDTO.getNewPassword(), changePasswordDTO.getConfirmPassword());
        if (isChanged) {
            return ResponseEntity.ok("Password successfully updated");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password change failed");
        }
    }
}
