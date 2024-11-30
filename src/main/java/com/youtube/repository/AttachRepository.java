package com.youtube.repository;

import com.youtube.entity.AttachEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AttachRepository extends JpaRepository<AttachEntity, String> {
    Optional<AttachEntity> findById(String attachId);

}
