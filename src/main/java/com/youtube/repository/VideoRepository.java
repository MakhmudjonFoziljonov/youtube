package com.youtube.repository;

import com.youtube.entity.VideoEntity;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<VideoEntity,String> {
}
