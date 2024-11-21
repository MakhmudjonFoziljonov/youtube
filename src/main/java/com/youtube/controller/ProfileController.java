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
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }
    @PostMapping("")
    public ResponseEntity<ProfileDTO> addProfile(@RequestBody @Valid ProfileCreationDTO requestDTO) {
        return ResponseEntity.status(201).body(profileService.createProfile(requestDTO));
    @PostMapping("/registration")
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileDTO dto) {
        ProfileDTO result = profileService.registration(dto);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/change-password")
     public ResponseEntity<String> changePassword(@RequestBody @Valid ChangePasswordDTO changePasswordDTO) {
        boolean isChanged = profileService.changePassword(changePasswordDTO.getOldPassword(), changePasswordDTO.getNewPassword(), changePasswordDTO.getConfirmPassword());
        if (isChanged) {
            return ResponseEntity.ok("Password successfully updated");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password change failed");

    @PostMapping("/authorization")
    public ResponseEntity<AuthResponseDTO> authorization(@RequestBody AuthRequestDTO dto,
                                                         @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLang lang) {
        AuthResponseDTO result = profileService.authorization(dto,lang);
        return ResponseEntity.ok(result);
    }
}
