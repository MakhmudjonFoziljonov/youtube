package com.youtube.repository;

import com.youtube.entity.AttachEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AttachRepository extends JpaRepository<AttachEntity, String> {
}
