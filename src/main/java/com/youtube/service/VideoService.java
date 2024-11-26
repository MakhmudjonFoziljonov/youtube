package com.youtube.service;

import com.youtube.dto.VideoDTO;
import com.youtube.entity.AttachEntity;
import com.youtube.entity.VideoEntity;
import com.youtube.enums.PlayListStatus;
import com.youtube.repository.AttachRepository;
import com.youtube.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final AttachRepository attachRepository;

    public VideoService(VideoRepository videoRepository, AttachRepository attachRepository) {
        this.videoRepository = videoRepository;
        this.attachRepository = attachRepository;
    }

    public VideoDTO createVideo(MultipartFile file, VideoDTO videoDTO) throws IOException {

        AttachEntity attach = new AttachEntity();
        attach.setId(UUID.randomUUID().toString());
        attach.setOriginName(file.getOriginalFilename());
        attach.setSize(file.getSize());
        attach.setType(file.getContentType());
        attach.setPath(saveFileToSystem(file));
        attach.setDuration(0.0);
        attach.setCreatedDate(LocalDateTime.now());

        AttachEntity savedAttach = attachRepository.save(attach);

        VideoEntity videoEntity = new VideoEntity();
        videoEntity.setId(UUID.randomUUID().toString());
        videoEntity.setTitle(videoDTO.getTitle());
        videoEntity.setDescription(videoDTO.getDescription());
        // videoEntity.setAttachId(savedAttach.getId());
        // videoEntity.setChannelId(videoDTO.getChannelId()); // Kanal ID.
        videoEntity.setCategoryId(videoDTO.getCategoryId()); // Kategoriya ID.
        videoEntity.setCreatedDate(LocalDateTime.now());
        videoEntity.setViewCount(0L);
        videoEntity.setLikeCount(0L);
        videoEntity.setDislikeCount(0L);

        VideoEntity savedVideo = videoRepository.save(videoEntity);
        return convertToDTO(savedVideo, savedAttach);
    }

    public void updateVideoDetails(String videoId, VideoDTO videoDTO, String userID) throws IOException {
        Optional<VideoEntity> optionalVideo = videoRepository.findById(videoId);

        if (optionalVideo.isEmpty()) {
            throw new RuntimeException("Video topilmadi!");
        }

        VideoEntity video = optionalVideo.get();

        if (!video.getChannelId().equals(userID)) {
            throw new RuntimeException("Siz faqat o'zingizning videongizni yangilashingiz mumkin!");
        }

        video.setTitle(videoDTO.getTitle());
        video.setDescription(videoDTO.getDescription());
        video.setCategoryId(videoDTO.getCategoryId());

        videoRepository.save(video);
    }



    public void changeStatus(String videoId, PlayListStatus newStatus, String userID) throws IOException {
        Optional<VideoEntity> optionalVideo = videoRepository.findById(videoId);
        if (optionalVideo.isEmpty()) {
            throw new RuntimeException("Video topilmadi!");
        }
        VideoEntity video = optionalVideo.get();
        if (!video.getChannelId().equals(userID)) {
            throw new RuntimeException("You are not authorized to change the video status.");
        }
        video.setStatus(newStatus);
        videoRepository.save(video);
    }

    public boolean incrementViewCount(String videoId) {
        Optional<VideoEntity> optionalVideo = videoRepository.findById(videoId);
        if (optionalVideo.isEmpty()) {
            throw new RuntimeException("Video topilmadi!");
        }
        VideoEntity video = optionalVideo.get();
        video.setViewCount(video.getViewCount() + 1);
        videoRepository.save(video);

        return true;
    }




    private VideoDTO convertToDTO(VideoEntity video, AttachEntity attach) {
        VideoDTO dto = new VideoDTO();
        dto.setId(video.getId());
        dto.setTitle(video.getTitle());
        dto.setDescription(video.getDescription());
        dto.setCreatedDate(video.getCreatedDate());
        dto.setAttachId(attach.getPath());
        dto.setCategoryId(video.getCategoryId()); // Kategoriya ID
        dto.setViewCount(video.getViewCount());
        dto.setLikeCount(video.getLikeCount());
        dto.setDislikeCount(video.getDislikeCount());

        return dto;
    }

    private String saveFileToSystem(MultipartFile file) throws IOException {
        String uploadDir = "uploads/";

        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

        Path filePath = Paths.get(uploadDir + fileName);

        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());

        return filePath.toString();
    }
}
