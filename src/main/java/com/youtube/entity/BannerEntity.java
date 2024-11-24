package com.youtube.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.XSlf4j;

@Entity
@Getter
@Setter
@Table(name = "banners")
public class BannerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "photo_id")
    private String photo_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", updatable = false, insertable = false)
    private AttachEntity photo;
}
