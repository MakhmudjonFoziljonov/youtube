package com.youtube.repository;

import com.youtube.entity.VideoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface VideoRepository extends CrudRepository<VideoEntity,String> {


    @Query("SELECT v FROM VideoEntity v WHERE v.category.id = :categoryId")
    Page<VideoEntity> findVideosByCategoryId(@Param("categoryId") String categoryId, Pageable pageable);

}
