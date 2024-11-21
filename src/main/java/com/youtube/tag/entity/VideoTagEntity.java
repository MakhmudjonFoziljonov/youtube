package com.youtube.tag.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "video_tag")
public class VideoTagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private VideoTagEntity video;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private TagEntity tag;

    private LocalDateTime createdDate;

}
