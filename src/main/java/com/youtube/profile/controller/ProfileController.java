package com.youtube.profile.controller;

import com.youtube.profile.dto.ChangePasswordDTO;
import com.youtube.profile.dto.ProfileCreationDTO;
import com.youtube.profile.dto.ProfileDTO;
import com.youtube.profile.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }
    @PostMapping("")
    public ResponseEntity<ProfileDTO> addProfile(@RequestBody @Valid ProfileCreationDTO requestDTO) {
        return ResponseEntity.status(201).body(profileService.createProfile(requestDTO));
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
