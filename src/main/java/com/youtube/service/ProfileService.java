package com.youtube.service;

import com.youtube.config.AppConfig;
import com.youtube.config.CustomUserDetails;
import com.youtube.dto.AuthRequestDTO;
import com.youtube.dto.AuthResponseDTO;
import com.youtube.dto.ProfileDTO;
import com.youtube.entity.ProfileEntity;
import com.youtube.enums.AppLang;
import com.youtube.repository.ProfileRepository;
import com.youtube.util.JwtUtil;
import com.youtube.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public ProfileDTO registration(ProfileDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleTrue(dto.getEmail());
        if (optional.isPresent()) {
            return null;
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        entity.setRole(dto.getRole());

        profileRepository.save(entity);

        dto.setId(String.valueOf(entity.getId()));
        return dto;
    }


    public AuthResponseDTO authorization(AuthRequestDTO auth, AppLang lang) throws UsernameNotFoundException {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPassword()));

            if (authentication.isAuthenticated()) {
                CustomUserDetails profile = (CustomUserDetails) authentication.getPrincipal();
                AuthResponseDTO response = new AuthResponseDTO();
                response.setName(profile.getName());
                response.setSurname(profile.getSurname());
                response.setEmail(profile.getEmail());
                response.setRole(profile.getRole());
                response.setJwtToken(JwtUtil.encode(profile.getEmail(), profile.getRole().name()));
                return response;
            }
        } catch (BadCredentialsException e) {
            throw new UsernameNotFoundException(resourceBundleService.getMessage("phone.or.password.wrong", lang));
        }
        throw new UsernameNotFoundException(resourceBundleService.getMessage("phone.or.password.wrong", lang));
    }
}