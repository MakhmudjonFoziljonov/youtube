package com.youtube.repository;

import com.youtube.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, String> {
    Optional<ProfileEntity> findByEmailAndVisibleTrue(String email);

    Optional<ProfileEntity> findById(String id);
}
