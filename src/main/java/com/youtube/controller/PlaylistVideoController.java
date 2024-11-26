package com.youtube.controller;

import com.youtube.entity.PlaylistVideoEntity;
import com.youtube.service.PlaylistVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/playlist-videos")
public class PlaylistVideoController {

    @Autowired
    private PlaylistVideoService service;

    @PostMapping
    public ResponseEntity<PlaylistVideoEntity> create(@RequestBody PlaylistVideoEntity entity) {
        return ResponseEntity.ok(service.create(entity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistVideoEntity> getById(@PathVariable Integer id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PlaylistVideoEntity>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaylistVideoEntity> update(@PathVariable Integer id, @RequestBody PlaylistVideoEntity entity) {
        return ResponseEntity.ok(service.update(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

