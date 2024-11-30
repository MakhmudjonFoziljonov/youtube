package com.youtube.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoLikeInfo {
    private String  id;
    private VideoDTO video;
    private AttachDTO previewAttach;
}
