package com.youtube.service;

import com.youtube.dto.VideoWatchedDTO;
import com.youtube.entity.VideoWatchedEntity;
import com.youtube.repository.VideoWatchedRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VideoWatchedService {


    private final VideoWatchedRepository videoWatchedRepository;

    public VideoWatchedService(VideoWatchedRepository videoWatchedRepository) {
        this.videoWatchedRepository = videoWatchedRepository;
    }

    public VideoWatchedDTO save(VideoWatchedEntity videoWatchedEntity) {
        VideoWatchedEntity savedEntity = videoWatchedRepository.save(videoWatchedEntity);
        return mapToDTO(savedEntity);
    }

    public Optional<VideoWatchedDTO> findById(String id) {
        Optional<VideoWatchedEntity> entity = videoWatchedRepository.findById(id);
        return entity.map(this::mapToDTO);
    }

    public List<VideoWatchedDTO> findAll() {
        List<VideoWatchedEntity> entities = (List<VideoWatchedEntity>) videoWatchedRepository.findAll();
        return entities.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public void deleteById(String id) {
        videoWatchedRepository.deleteById(id);
    }

    private VideoWatchedDTO mapToDTO(VideoWatchedEntity entity) {
        VideoWatchedDTO dto = new VideoWatchedDTO();
        dto.setId(entity.getId());
        dto.setVideoId(entity.getVideoId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setProfileId(entity.getProfile_id());
        return dto;
    }
}
