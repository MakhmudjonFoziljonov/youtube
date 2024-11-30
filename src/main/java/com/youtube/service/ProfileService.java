package com.youtube.service;


import com.youtube.dto.*;
import com.youtube.entity.ProfileEntity;
import com.youtube.enums.ProfileStatus;
import com.youtube.exp.AppBadRequestException;
import com.youtube.repository.ProfileRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final String photoBaseUrl = "http://localhost:8080/uploads/profile_photos/";




    public ProfileDTO createProfile(ProfileCreationDTO request) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleTrue(request.getEmail());
        if (optional.isPresent()) {
            throw new AppBadRequestException("Email already in use");
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(request.getName());
        entity.setSurname(request.getSurname());
        entity.setEmail(request.getEmail());
        entity.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        entity.setRole(request.getRole());
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setVisible(Boolean.TRUE);
        entity.setCreatedDate(LocalDateTime.now());
        profileRepository.save(entity);


        return mapToDTO(entity);
    }


    public ProfileDTO mapToDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

    public boolean updateDetail(@Valid UpdateProfileDetailDTO requestDTO, String username) {
        ProfileEntity profile = getByUsername(username);
        profile.setName(requestDTO.getName());
        profile.setSurname(requestDTO.getSurname());
        profileRepository.save(profile);

        return true;
    }
    public ProfileEntity getByUsername(String username) {
        return profileRepository.findByEmailAndVisibleTrue(username).orElseThrow(() -> new AppBadRequestException("User not found"));
    }

    public String updateMainPhoto(String  userId, MultipartFile photo)

            throws IOException, ChangeSetPersister.NotFoundException {
        ProfileEntity user = profileRepository.findById(userId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        if (user.getPhoto() != null) {
            File oldPhoto = new File(photo + user.getPhoto());
            if (oldPhoto.exists()) {
                oldPhoto.delete();
            }
            String newPhotoName = UUID.randomUUID().toString() + "_" + photo.getOriginalFilename();
            File newPhotoFile = new File(photo + newPhotoName);
            photo.transferTo(newPhotoFile);

            user.setPhoto(newPhotoName);
            profileRepository.save(user);

            return "Profile photo updated successfully.";
        }
        return " ";
    }
    public ResponseEntity<ProfileDTO> getProfileDetail(String userId) {
        Optional<ProfileEntity> optional = profileRepository.findById(userId);

        if (optional.isPresent()) {
            ProfileEntity profileEntity = optional.get();
            ProfileDTO profileDTO = new ProfileDTO();
            profileDTO.setId(profileEntity.getId());
            profileDTO.setName(profileEntity.getName());
            profileDTO.setSurname(profileEntity.getSurname());
            profileDTO.setEmail(profileEntity.getEmail());
            profileDTO.setPassword(profileEntity.getPassword());
            profileDTO.setStatus(profileEntity.getStatus());
            profileDTO.setRole(profileEntity.getRole());
            profileDTO.setCreatedDate(profileEntity.getCreatedDate());

            String photoUrl = generatePhotoUrl(profileEntity.getPhoto());
            profileDTO.setPhotoId(photoUrl);

            return ResponseEntity.ok(profileDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    public String generatePhotoUrl(String photoName) {
        if (photoName == null || photoName.isEmpty()) {
            return null;
        }
        return photoBaseUrl + photoName;
    }
    public boolean changePassword(String id,String oldPassword, String currentPassword, String confirmNewPassword) {
        Optional<ProfileEntity> optionalProfile = profileRepository.findById(id);
        if (optionalProfile.isPresent()) {
            ProfileEntity profile = optionalProfile.get();

            if (!bCryptPasswordEncoder.matches(oldPassword, profile.getPassword())) {
                return false;
            }

            if (!currentPassword.equals(confirmNewPassword)) {
                return false;
            }

            String encodedPassword = bCryptPasswordEncoder.encode(currentPassword);
            profile.setPassword(encodedPassword);

            profileRepository.save(profile);
            return true;
        }

        return false;
    }




}