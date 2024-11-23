package com.youtube.repository;

import com.youtube.entity.PlaylistEntity;
import com.youtube.enums.PlaylistStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Long> {
    List<PlaylistEntity> findByChannelId(Long channelId);
    List<PlaylistEntity> findByStatusAndChannelId(PlaylistStatusEnum status, Long channelId);
}
