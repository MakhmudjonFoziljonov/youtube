package com.youtube.repository;

import com.youtube.entity.TagEntity;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<TagEntity,String> {

}
