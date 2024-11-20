package com.youtube.tag.repository;

import com.youtube.tag.entity.TagEntity;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<TagEntity,Integer> {

}
