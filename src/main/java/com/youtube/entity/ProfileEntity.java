package com.youtube.entity;

import com.youtube.enums.ProfileRole;
import com.youtube.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "profile_entity")
public class ProfileEntity {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private ProfileStatus status;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private ProfileRole role;

    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "photo")
    private String photo;
//
//    @Column(name = "photo_id")
//    private String photoId;
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "photo_id", insertable = false, updatable = false)
//    private AttachEntity photo;
}
