package com.youtube.service;

import com.youtube.entity.PlaylistVideoEntity;
import com.youtube.repository.PlaylistVideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


@Service
public class PlaylistVideoService {

    @Autowired
    private PlaylistVideoRepository repository;

    public PlaylistVideoEntity create(PlaylistVideoEntity entity) {
        return repository.save(entity);
    }

    public Optional<PlaylistVideoEntity> getById(Integer id) {
        return repository.findById(id);
    }

    public List<PlaylistVideoEntity> getAll() {
        return repository.findAll();
    }

    public PlaylistVideoEntity update(Integer id, PlaylistVideoEntity updatedEntity) {
        return repository.findById(id).map(entity -> {
            entity.setPlaylistId(updatedEntity.getPlaylistId());
            entity.setVideoId(updatedEntity.getVideoId());
            entity.setOrderNum(updatedEntity.getOrderNum());
            return repository.save(entity);
        }).orElseThrow(() -> new RuntimeException("PlaylistVideo not found with id " + id));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
