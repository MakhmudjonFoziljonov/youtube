package com.youtube.repository;

import com.youtube.entity.ChannelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ChannelRepository extends CrudRepository<ChannelEntity,String> {
    Page<ChannelEntity> findAll(Pageable pageable);

}
