package com.youtube.service;

import com.youtube.dto.AttachDTO;
import com.youtube.dto.VideoDTO;
import com.youtube.dto.VideoLikeInfo;
import com.youtube.entity.AttachEntity;
import com.youtube.entity.VideoEntity;
import com.youtube.entity.VideoLikeEntity;
import com.youtube.repository.AttachRepository;
import com.youtube.repository.VideoLikeRepository;
import com.youtube.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoLikeService {

    private final VideoLikeRepository videoLikeRepository;
    private final AttachRepository attachRepository;
private final VideoRepository videoRepository;
    public VideoLikeService(VideoLikeRepository videoLikeRepository, AttachRepository attachRepository,  VideoRepository videoRepository) {
        this.videoLikeRepository = videoLikeRepository;
        this.attachRepository = attachRepository;
        this.videoRepository = videoRepository;
    }

    public VideoLikeEntity saveLike(String profileId, String videoId) {
        VideoLikeEntity videoLikeEntity = new VideoLikeEntity();
        videoLikeEntity.setVideoId(videoId);
        videoLikeEntity.setProfileId(profileId);

        return videoLikeRepository.save(videoLikeEntity);

    }

    public void removeLike(String id) {
        videoLikeRepository.deleteById(id);
    }

    public List<VideoLikeInfo> getUserLikedVideos(String profileId) {
        List<VideoLikeEntity> videoLikes = videoLikeRepository.findByProfileIdOrderByCreatedDateDesc(profileId);
        return videoLikes.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    private VideoLikeInfo mapToDto(VideoLikeEntity videoLike) {
        VideoEntity videoEntity = videoRepository.findById(videoLike.getVideoId())
                .orElseThrow(() -> new RuntimeException("Video not found"));

        VideoDTO videoDto = new VideoDTO();
        videoDto.setId(videoEntity.getId());
        videoDto.setTitle(videoEntity.getTitle());
        videoDto.setDescription(videoEntity.getDescription());
        videoDto.setCreatedDate(videoEntity.getCreatedDate());

        AttachEntity attachEntity = attachRepository.findById(videoEntity.getAttachId())
                .orElseThrow(() -> new RuntimeException("Attach not found"));

        AttachDTO attachDto = new AttachDTO();
        attachDto.setId(attachEntity.getId());
        attachDto.setOriginName(attachEntity.getOriginName());
        attachDto.setSize(attachEntity.getSize());
        attachDto.setType(attachEntity.getType());
        attachDto.setPath(attachEntity.getPath());
        attachDto.setCreatedDate(attachEntity.getCreatedDate());
        attachDto.setVisible(attachEntity.getVisible());
        attachDto.setDuration(attachEntity.getDuration());

        VideoLikeInfo videoLikeInfo = new VideoLikeInfo();
        videoLikeInfo.setVideo(videoDto);
        videoLikeInfo.setPreviewAttach(attachDto);

        if (videoEntity.getPreviewAttachId() != null) {
            AttachEntity previewAttachEntity = attachRepository.findById(videoEntity.getPreviewAttachId())
                    .orElseThrow(() -> new RuntimeException("Preview attach not found"));

            AttachDTO previewAttachDto = new AttachDTO();
            previewAttachDto.setId(previewAttachEntity.getId());
            previewAttachDto.setOriginName(previewAttachEntity.getOriginName());
            previewAttachDto.setSize(previewAttachEntity.getSize());
            previewAttachDto.setType(previewAttachEntity.getType());
            previewAttachDto.setPath(previewAttachEntity.getPath());
            previewAttachDto.setCreatedDate(previewAttachEntity.getCreatedDate());
            previewAttachDto.setVisible(previewAttachEntity.getVisible());
            previewAttachDto.setDuration(previewAttachEntity.getDuration());

            videoLikeInfo.setPreviewAttach(previewAttachDto);
        }

        return videoLikeInfo;
    }




}
