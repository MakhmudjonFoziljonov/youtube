package com.youtube.controller;

import com.youtube.dto.TagDTO;
import com.youtube.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/v1")
public class TagController {

    private final TagService tagService;
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }
    @PostMapping("/create/tag")
    @Operation(
            summary = "Create a new tag",
            description = "Allows the creation of a new tag. The tag details must be provided in the request body.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public void createTag(@RequestBody TagDTO tagDTO) {
         tagService.create(tagDTO);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Update an existing tag",
            description = "Allows an admin to update a tag's details by its ID.",
            security = @SecurityRequirement(name = "bearerAuth")
    )

    public void updateTag(@PathVariable Integer id, @RequestBody TagDTO tagDTO) {
          tagService.update(id, tagDTO);
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Delete a tag",
            description = "Allows an admin to delete a tag by its ID.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public void deleteTag(@PathVariable Integer id) {
       tagService.delete(id);
    }

    @GetMapping("/all")
    @Operation(
            summary = "Fetch all tags",
            description = "Retrieves a list of all tags from the system."
    )
    public ResponseEntity<?> getAllTags() {
        try {
            List<TagDTO> tags = tagService.getAllTags();
            return ResponseEntity.ok(tags);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while fetching tags: " + e.getMessage());
        }
    }
}
