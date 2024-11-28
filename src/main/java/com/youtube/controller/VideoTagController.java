package com.youtube.controller;

import com.youtube.dto.VideoTagDTO;
import com.youtube.service.VideoTagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/video-tags")
public class VideoTagController {


    private final VideoTagService videoTagService;

    public VideoTagController(VideoTagService videoTagService) {
        this.videoTagService = videoTagService;
    }

    // 1. Add tag to video
    @PostMapping("/add")
    public ResponseEntity<VideoTagDTO> addTagToVideo(@RequestParam String videoId, @RequestParam String tagId) {
        try {
            VideoTagDTO videoTagDTO = videoTagService.addTagToVideo(videoId, tagId);
            return new ResponseEntity<>(videoTagDTO, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // 2. Delete tag from video
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteTagFromVideo(@RequestParam String videoId, @RequestParam String tagId) {
        try {
            videoTagService.deleteTagFromVideo(videoId, tagId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 3. Get all tags for a specific video
    @GetMapping("/list/{videoId}")
    public ResponseEntity<List<VideoTagDTO>> getTagsByVideoId(@PathVariable String videoId) {
        List<VideoTagDTO> videoTagDTOs = videoTagService.getTagsByVideoId(videoId);
        return new ResponseEntity<>(videoTagDTOs, HttpStatus.OK);
    }
}
