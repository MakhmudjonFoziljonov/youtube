package com.youtube.profile.repository;

import com.youtube.profile.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity,Integer> {
    Optional<ProfileEntity> findByEmailAndVisibleTrue(String email);

    Optional<ProfileEntity> findByPassword(String password);

}
