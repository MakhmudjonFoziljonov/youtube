package com.youtube.controller;

import com.youtube.dto.*;
import com.youtube.enums.AppLang;
import com.youtube.service.ProfileService;
import com.youtube.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("")
    public ResponseEntity<ProfileDTO> addProfile(@RequestBody @Valid ProfileCreationDTO requestDTO) {
        return ResponseEntity.status(201).body(profileService.createProfile(requestDTO));
    }



    @PutMapping("/change/password")
    public void changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {

    }

    @PutMapping("/detail")
    public ResponseEntity<Boolean> updateDetail(@RequestBody @Valid UpdateProfileDetailDTO requestDTO,
                                                @RequestHeader("Authorization") String token) {
        JwtDTO dto = JwtUtil.decode(token.substring(7));
        return ResponseEntity.ok().body(profileService.updateDetail(requestDTO, dto.getUsername()));
    }

}
