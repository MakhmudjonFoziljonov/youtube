package com.youtube.controller;

import com.youtube.dto.VideoLikeInfo;
import com.youtube.entity.VideoLikeEntity;
import com.youtube.service.VideoLikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class VideoLikeController {
    private final VideoLikeService videoLikeService;

    public VideoLikeController(VideoLikeService videoLikeService) {
        this.videoLikeService = videoLikeService;
    }

    @PostMapping
    public ResponseEntity<VideoLikeEntity> createLike(@RequestParam String profileId, @RequestParam String videoId) {
        VideoLikeEntity videoLike = videoLikeService.saveLike(profileId, videoId);
        return ResponseEntity.ok(videoLike);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeLike(@PathVariable String id) {
        videoLikeService.removeLike(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{profileId}")
    public ResponseEntity<List<VideoLikeInfo>> getUserLikedVideos(@PathVariable String profileId) {
        List<VideoLikeInfo> likedVideos = videoLikeService.getUserLikedVideos(profileId);
        return ResponseEntity.ok(likedVideos);
    }
}
