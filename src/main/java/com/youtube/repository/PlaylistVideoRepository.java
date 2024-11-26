package com.youtube.repository;

import com.youtube.entity.PlaylistVideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistVideoRepository extends JpaRepository<PlaylistVideoEntity, Integer> {
}
