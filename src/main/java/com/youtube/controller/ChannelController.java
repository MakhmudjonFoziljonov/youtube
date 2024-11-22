package com.youtube.controller;

import com.youtube.dto.ChannelDTO;
import com.youtube.dto.ChannelPhotoDTO;
import com.youtube.entity.ChannelEntity;
import com.youtube.service.ChannelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class ChannelController {
    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @PostMapping("/channel/create")
    public ResponseEntity<String> create(@RequestBody ChannelDTO channelDTO) {
        if (channelDTO.getName() == null || channelDTO.getName().isEmpty() ||
                channelDTO.getDescription() == null || channelDTO.getDescription().isEmpty()) {
            return new ResponseEntity<>("Channel name or description cannot be empty", HttpStatus.BAD_REQUEST);
        }

        try {
            channelService.createChannel(channelDTO);
            return new ResponseEntity<>("Channel created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating channel: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/channel/update")
    public ResponseEntity<String> update(@RequestBody ChannelDTO channelDTO) {
        if (channelDTO.getName() == null || channelDTO.getName().isEmpty()
                || channelDTO.getDescription() == null || channelDTO.getDescription().isEmpty()) {
            return new ResponseEntity<>("Channel name and description cannot be empty", HttpStatus.BAD_REQUEST);

        }
        try {
            channelService.updateChannel(channelDTO);
            return new ResponseEntity<>("Channel update successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating channel: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/id/photo")
    public ResponseEntity<String> updatePhoto(@PathVariable String channelId,
                                              @RequestBody ChannelPhotoDTO channelPhotoDTO,
                                              @RequestHeader("X-User-Id") String profileId,
                                              @RequestHeader("X-Role") String role) {
        try {
            Optional<ChannelEntity> updatedChannel = channelService.updateChannelPhoto(channelId, channelPhotoDTO, profileId, role);
            return new ResponseEntity<>("Channel photo updated successfully", HttpStatus.OK);
        } catch (SecurityException e) {
            return new ResponseEntity<>("Unauthorized: " + e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/id/banner")
    public ResponseEntity<String> updateBanner(@PathVariable String channelId,
                                              @RequestBody ChannelPhotoDTO channelPhotoDTO,
                                              @RequestHeader("X-User-Id") String profileId,
                                              @RequestHeader("X-Role") String role) {
        try {
            Optional<ChannelEntity> updatedChannel = channelService.updateChannelBanner(channelId, channelPhotoDTO, profileId, role);
            return new ResponseEntity<>("Channel photo updated successfully", HttpStatus.OK);
        } catch (SecurityException e) {
            return new ResponseEntity<>("Unauthorized: " + e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



