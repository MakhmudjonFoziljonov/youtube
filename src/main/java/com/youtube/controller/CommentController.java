package com.youtube.controller;

import com.youtube.dto.CommentDto;
import com.youtube.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER','MODERATOR')")
    public ResponseEntity<CommentDto> create(@RequestBody CommentDto dto) {
        return ResponseEntity.ok(commentService.create(dto));
    }
}