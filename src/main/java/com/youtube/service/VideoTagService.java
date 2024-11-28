package com.youtube.service;

import com.youtube.dto.VideoTagDTO;
import com.youtube.entity.VideoTagEntity;
import com.youtube.repository.TagRepository;
import com.youtube.repository.VideoRepository;
import com.youtube.repository.VideoTagRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoTagService {


    private final VideoTagRepository videoTagRepository;


    private final VideoRepository videoRepository;


    private final TagRepository tagRepository;

    public VideoTagService(VideoTagRepository videoTagRepository, VideoRepository videoRepository, TagRepository tagRepository) {
        this.videoTagRepository = videoTagRepository;
        this.videoRepository = videoRepository;
        this.tagRepository = tagRepository;
    }

    // 1. Add tag to video
    public VideoTagDTO addTagToVideo(String videoId, String tagId) {
        if (videoTagRepository.existsByVideoIdAndTagId(videoId, tagId)) {
            throw new RuntimeException("Tag already added to this video");
        }

        VideoTagEntity videoTagEntity = new VideoTagEntity();
        videoTagEntity.setVideoId(videoId);
        videoTagEntity.setTagId(tagId);
        videoTagEntity.setCreatedDate(LocalDateTime.now());

        videoTagEntity = videoTagRepository.save(videoTagEntity);
        return mapToDTO(videoTagEntity);
    }

    public void deleteTagFromVideo(String videoId, String tagId) {
        if (!videoTagRepository.existsByVideoIdAndTagId(videoId, tagId)) {
            throw new RuntimeException("Tag not found for this video");
        }

        videoTagRepository.deleteByVideoIdAndTagId(videoId, tagId);
    }

    public List<VideoTagDTO> getTagsByVideoId(String videoId) {
        List<VideoTagEntity> videoTagEntities = videoTagRepository.findByVideoId(videoId);
        return videoTagEntities.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private VideoTagDTO mapToDTO(VideoTagEntity entity) {
        VideoTagDTO dto = new VideoTagDTO();
        dto.setVideoId(entity.getVideoId());
        dto.setTagId(entity.getTagId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;

        }
}
