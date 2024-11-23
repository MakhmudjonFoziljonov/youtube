package com.youtube.controller;

import com.youtube.dto.*;
import com.youtube.entity.ProfileEntity;
import com.youtube.enums.AppLang;
import com.youtube.service.ProfileService;
import com.youtube.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @PutMapping("/update-photo")
    public ResponseEntity<String> updateMainPhoto(
            @RequestHeader("userId") Integer userId,
            @RequestParam("photo") MultipartFile photo) {

        try {
            String message = profileService.updateMainPhoto(userId, photo);
            return ResponseEntity.ok(message);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload photo.");
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable Integer id) {
        ProfileDTO profile = profileService.getProfileDetail(id).getBody();

        if (profile != null) {
            return ResponseEntity.ok(profile);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
