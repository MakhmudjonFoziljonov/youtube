package com.youtube.controller;

import com.youtube.dto.VideoWatchedDTO;
import com.youtube.entity.VideoWatchedEntity;
import com.youtube.service.VideoWatchedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/video-watched")
public class VideoWatchedController {


    private final VideoWatchedService videoWatchedService;

    public VideoWatchedController(VideoWatchedService videoWatchedService) {
        this.videoWatchedService = videoWatchedService;
    }

    // Create or Update VideoWatchedEntity
    @PostMapping
    public ResponseEntity<VideoWatchedDTO> createOrUpdate(@RequestBody VideoWatchedEntity videoWatchedEntity) {
        VideoWatchedDTO savedEntity = videoWatchedService.save(videoWatchedEntity);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    // Get VideoWatchedEntity by ID
    @GetMapping("/{id}")
    public ResponseEntity<VideoWatchedDTO> getById(@PathVariable String id) {
        Optional<VideoWatchedDTO> videoWatchedDTO = videoWatchedService.findById(id);
        return videoWatchedDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all VideoWatchedEntities
    @GetMapping
    public List<VideoWatchedDTO> getAll() {
        return videoWatchedService.findAll();
    }

    // Delete VideoWatchedEntity by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        videoWatchedService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
