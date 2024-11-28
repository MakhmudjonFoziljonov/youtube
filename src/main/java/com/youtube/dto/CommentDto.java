package com.youtube.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private String content;
    private String articleId;
    private String replyId;
}
