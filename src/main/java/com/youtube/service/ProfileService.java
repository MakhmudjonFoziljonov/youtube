package com.youtube.service;

import com.youtube.config.AppConfig;
import com.youtube.config.CustomUserDetails;
import com.youtube.dto.*;
import com.youtube.entity.ProfileEntity;
import com.youtube.enums.AppLang;
import com.youtube.enums.ProfileStatus;
import com.youtube.exp.AppBadRequestException;
import com.youtube.repository.ProfileRepository;
import com.youtube.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ResourceBundleService resourceBundleService;

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
}