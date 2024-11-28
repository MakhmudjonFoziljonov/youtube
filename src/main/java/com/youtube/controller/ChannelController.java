package com.youtube.controller;

import com.youtube.dto.ChangeChannelStatusDTO;
import com.youtube.dto.ChannelDTO;
import com.youtube.dto.ChannelPhotoDTO;
import com.youtube.entity.ChannelEntity;
import com.youtube.service.ChannelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
@Tag(name = "channel  API", description = "channel bilan ishlash uchun API")

public class ChannelController {
    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @PostMapping("/channel/create")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(
            summary = "Create a new channel",
            description = "This endpoint allows a user with ROLE_USER to create a new channel. The request body must contain channel name and description."
    )
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
    @Operation(
            summary = "Update channel details",
            description = "This endpoint allows updating an existing channel's name and description. Both fields are required in the request body."
    )
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
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(
            summary = "Update channel photo",
            description = "Allows a user with ROLE_USER to update the photo of a channel. The request requires channel ID, photo details, and user role in the headers."
    )
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
    @Operation(
            summary = "Update channel banner",
            description = "Allows updating the banner of a channel. The request requires channel ID, banner details, and user role in the headers."
    )

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

    @GetMapping("/page")
    @PreAuthorize("hasRole('Admin')")
    @Operation(
            summary = "Retrieve paginated list of channels",
            description = "Admin-only endpoint to retrieve a paginated and sorted list of channels."
    )


    public ResponseEntity<Page<ChannelEntity>> getChannels(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "desc") String sortDirection) {

        Page<ChannelEntity> channels = channelService.getChannels(page, size,  sortDirection);
        return ResponseEntity.ok(channels);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Retrieve a channel by ID",
            description = "Fetch details of a specific channel by its unique ID."
    )
    public Optional<ChannelEntity> getChannel(@PathVariable String id) {

    return channelService.getById(id);
    }

    @PutMapping("/channel-status")
    @Operation(
            summary = "Change channel status",
            description = "Allows changing the status of a channel. The request requires user ID and role in the headers."
    )
    public ResponseEntity<String> changeChannelStatus(
            @RequestHeader("userId") Long userId,
            @RequestHeader("role") String role,
            @RequestBody ChangeChannelStatusDTO dto) throws ChangeSetPersister.NotFoundException {
        String message = channelService.changeChannelStatus(userId, role, dto);
        return ResponseEntity.ok(message);
    }
}



