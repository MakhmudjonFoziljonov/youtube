package com.youtube.repository;

import com.youtube.entity.PlayListEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayListRepository extends CrudRepository<PlayListEntity, String> {
}
