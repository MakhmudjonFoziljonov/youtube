package com.youtube.controller;

import com.youtube.entity.CommentLikeEntity;
import com.youtube.enums.LikeType;
import com.youtube.service.CommentLikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/api")
public class CommentLikeController {
    private final CommentLikeService commentLikeService;

    public CommentLikeController(CommentLikeService commentLikeService) {
        this.commentLikeService = commentLikeService;
    }
    @PostMapping("/create")
    public CommentLikeEntity createCommentLike(@RequestParam String profileId,
                                               @RequestParam String commentId,
                                               @RequestParam LikeType type) {
        return commentLikeService.createCommentLike(profileId, commentId, type);
    }

    @DeleteMapping("/remove")
    public void removeCommentLike(@RequestParam String profileId,
                                  @RequestParam String commentId) {
        commentLikeService.removeCommentLike(profileId, commentId);
    }

    @GetMapping("/user-liked/{profileId}")
    public List<CommentLikeEntity> getUserLikedComments(@PathVariable String profileId) {
        return commentLikeService.getUserLikedComments(profileId);
    }

    @GetMapping("/admin/{profileId}")
    public List<CommentLikeEntity> getUserLikedCommentsByUserId(@PathVariable String profileId) {
        return commentLikeService.getUserLikedCommentsByUserId(profileId);
    }
}
