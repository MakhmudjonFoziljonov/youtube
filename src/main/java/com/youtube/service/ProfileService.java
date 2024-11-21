package com.youtube.service;

import com.youtube.dto.ProfileCreationDTO;
import com.youtube.dto.ProfileDTO;
import com.youtube.entity.ProfileEntity;
import com.youtube.enums.ProfileStatus;
import com.youtube.exp.AppBadRequestException;
import com.youtube.repository.ProfileRepository;
import com.youtube.util.MD5Util;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileDTO createProfile(ProfileCreationDTO request) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleTrue(request.getEmail());
        if (optional.isPresent()) {
            throw new AppBadRequestException("Email already in use");
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(request.getName());
        entity.setSurname(request.getSurname());
        entity.setEmail(request.getEmail());
        entity.setPassword(MD5Util.md5(request.getPassword()));
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
//        dto.setPhoto(attachService.getDTO(entity.getPhotoId()));

        return dto;
    }

    public boolean changePassword(String oldPassword, String newPassword, String confirmPassword) {

        if (!newPassword.equals(confirmPassword)) {
            return false;
        }
      Optional<ProfileEntity> profile=profileRepository.findByPassword(oldPassword);
        if (profile == null) {
            return false;
        }
        ProfileEntity profile1=new ProfileEntity();
        profile1.setPassword(newPassword);
        profileRepository.save(profile1);
        return true;

    }
}
