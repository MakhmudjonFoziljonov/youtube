package com.youtube.service;

import com.youtube.config.CustomUserDetails;
import com.youtube.entity.ProfileEntity;
import com.youtube.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username = login or phone or email
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleTrue(username);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        ProfileEntity profile = optional.get();
        return new CustomUserDetails(profile);
    }
}
