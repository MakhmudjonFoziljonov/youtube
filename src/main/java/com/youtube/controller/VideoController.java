package com.youtube.controller;

import com.youtube.dto.VideoDTO;
import com.youtube.dto.VideoShortInfo;
import com.youtube.enums.PlayListStatus;
import com.youtube.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(
            summary = "Upload a new video",
            description = "This endpoint allows users to upload a new video. The video details (e.g., title, description, etc.) should be provided in the request body."
    )
    public ResponseEntity<VideoDTO> uploadVideo(@RequestBody  VideoDTO videoDTO) {
        try {
            return ResponseEntity.ok(videoService.createVideo( videoDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @PutMapping("/{videoId}/details")
    @Operation(
            summary = "Update video details",
            description = "Allows updating the details (e.g., title, description) of an existing video. Requires the video ID, new details, and user ID."
    )
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
    @Operation(
            summary = "Change video status",
            description = "Allows changing the status of a video (e.g., PUBLIC or PRIVATE). Requires the video ID, new status, and user ID."
    )
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
    @Operation(
            summary = "Increment video view count",
            description = "This endpoint increments the view count of a video by its ID. Returns an error if the video is not found."
    )
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
    @Operation(
            summary = "Get videos by category",
            description = "Retrieve a paginated list of videos that belong to a specific category. The category ID, page number, and page size must be provided."
    )
    public ResponseEntity<Page<VideoShortInfo>> getVideos(@PathVariable("categoryId") String categoryId,
                                                          @RequestParam("page") int page,
                                                          @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<VideoShortInfo> videoShortInfos = videoService.getVideosByCategoryId(categoryId, pageable);
        return ResponseEntity.ok(videoShortInfos);
    }
}
