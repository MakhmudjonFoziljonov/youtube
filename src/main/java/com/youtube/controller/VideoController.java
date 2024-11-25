package com.youtube.controller;

import com.youtube.dto.VideoDTO;
import com.youtube.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }
    @PostMapping("/upload")
     public ResponseEntity<VideoDTO> uploadVideo(  @RequestParam("file") MultipartFile file, VideoDTO videoDTO) {
        try {
            return ResponseEntity.ok(videoService.createVideo(file,videoDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
