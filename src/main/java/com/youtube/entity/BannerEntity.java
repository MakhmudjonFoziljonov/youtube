package com.youtube.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "banners")
public class BannerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "photo_id")
    private String photoId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", insertable = false, updatable = false)
    private AttachEntity attach;
}
