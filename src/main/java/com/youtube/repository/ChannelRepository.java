package com.youtube.repository;

import com.youtube.entity.ChannelEntity;
import org.springframework.data.repository.CrudRepository;

public interface ChannelRepository extends CrudRepository<ChannelEntity,String> {

}
