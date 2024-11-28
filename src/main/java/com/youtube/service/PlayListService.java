package com.youtube.service;

import com.youtube.dto.PlayListInfoDTO;
import com.youtube.entity.PlayListEntity;
import com.youtube.repository.PlayListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayListService {
    private final PlayListRepository playListRepository;

    public PlayListInfoDTO create(PlayListInfoDTO dto) {
        var entity = new PlayListEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setOrderNumber(dto.getOrderNum());
        entity.setPlayListStatus(dto.getStatus());
        entity.setChannelId(dto.getChannelId());
        playListRepository.save(entity);
        return dto;
    }
}
