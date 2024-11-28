package com.youtube.controller;

import com.youtube.dto.*;

import com.youtube.service.ProfileService;
import com.youtube.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;

import com.youtube.dto.ChangePasswordDTO;
import com.youtube.dto.ProfileDTO;

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
    @Operation(
            summary = "Create a new profile",
            description = "This endpoint allows creating a new user profile. The required profile details must be sent in the request body."
    )
    public ResponseEntity<ProfileDTO> addProfile(@RequestBody @Valid ProfileCreationDTO requestDTO) {
        return ResponseEntity.status(201).body(profileService.createProfile(requestDTO));
    }

    @PutMapping("/detail")
    @Operation(
            summary = "Update profile details",
            description = "Allows updating the profile details of the currently authenticated user. Requires a valid JWT token in the Authorization header."
    )
    public ResponseEntity<Boolean> updateDetail(@RequestBody @Valid UpdateProfileDetailDTO requestDTO,
                                                @RequestHeader("Authorization") String token) {
        JwtDTO dto = JwtUtil.decode(token.substring(7));
        return ResponseEntity.ok().body(profileService.updateDetail(requestDTO, dto.getUsername()));
    }

    @PutMapping("/update-photo")
    @Operation(
            summary = "Update main profile photo",
            description = "Allows the user to update their main profile photo. Requires the user ID in the header and the photo file in the request."
    )
    public ResponseEntity<String> updateMainPhoto(
            @RequestHeader("userId") String userId,
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
    @Operation(
            summary = "Get profile by ID",
            description = "Fetches the profile details of a user by their ID. Returns a 404 status if the profile is not found."
    )
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable String id) {
        ProfileDTO profile = profileService.getProfileDetail(id).getBody();

        if (profile != null) {
            return ResponseEntity.ok(profile);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PostMapping("/change-password/{id}")
    @Operation(
            summary = "Change user password",
            description = "Allows the user to change their password. Requires the user ID and the current password, new password, and confirmation of the new password in the request body."
    )
    public ResponseEntity<String> changePassword(
            @PathVariable("id") String id,
            @RequestBody @Valid ChangePasswordDTO changePasswordDTO) {
        boolean isChanged = profileService.changePassword(
                id,
                changePasswordDTO.getCurrentPassword(),
                changePasswordDTO.getNewPassword(),
                changePasswordDTO.getConfirmNewPassword()
        );

        if (isChanged) {
            return ResponseEntity.ok("Password successfully updated");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password change failed");
        }
    }
}