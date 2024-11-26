package com.youtube.controller;

import com.youtube.dto.VideoDTO;
import com.youtube.dto.VideoShortInfo;
import com.youtube.enums.PlayListStatus;
import com.youtube.service.VideoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/upload")
    public ResponseEntity<VideoDTO> uploadVideo(@RequestBody MultipartFile file, VideoDTO videoDTO) {
        try {
            return ResponseEntity.ok(videoService.createVideo(file, videoDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @PutMapping("/{videoId}/details")
    public ResponseEntity<String> updateVideoDetails(
            @PathVariable String videoId,
            @RequestBody VideoDTO videoDTO,
            @RequestParam String userId){
        try {
            videoService.updateVideoDetails(videoId, videoDTO, userId);

            return ResponseEntity.ok("Video detallar muvaffaqiyatli yangilandi.");
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @PutMapping("/{videoId}/status")
    public ResponseEntity<String> changeVideoStatus(
            @PathVariable String videoId,
            @RequestParam PlayListStatus newStatus,
            @RequestParam String userId) throws IOException {
        try {
            videoService.changeStatus(videoId, newStatus, userId);
            return ResponseEntity.ok("Video status successfully updated.");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}/incrementView")
    public ResponseEntity<String> incrementViewCount(@PathVariable("id") String videoId) {
        boolean isUpdated = videoService.incrementViewCount(videoId);
        if (isUpdated) {
            return ResponseEntity.ok("View count incremented successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Video not found with ID: " + videoId);
        }
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<VideoShortInfo>> getVideos(@PathVariable("categoryId") String categoryId,
                                                          @RequestParam("page") int page,
                                                          @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<VideoShortInfo> videoShortInfos = videoService.getVideosByCategoryId(categoryId, pageable);
        return ResponseEntity.ok(videoShortInfos);
    }
}
