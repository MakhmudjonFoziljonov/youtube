package com.youtube.tag.controller;

import com.youtube.tag.dto.TagDTO;
import com.youtube.tag.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
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
    public void createTag(@RequestBody TagDTO tagDTO) {
         tagService.create(tagDTO);
    }

    @PutMapping("/update/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public void updateTag(@PathVariable Integer id, @RequestBody TagDTO tagDTO) {
          tagService.update(id, tagDTO);
    }
    @DeleteMapping("{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTag(@PathVariable Integer id) {
       tagService.delete(id);
    }

    @GetMapping("/all")
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
