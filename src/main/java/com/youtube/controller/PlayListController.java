package com.youtube.controller;

import com.youtube.dto.PlayListInfoDTO;
import com.youtube.service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/play-list")
public class PlayListController {
    @Autowired
    private PlayListService playListService;

    @PostMapping("/create")
    public ResponseEntity<PlayListInfoDTO> create(@RequestBody PlayListInfoDTO dto) {
        return ResponseEntity.ok(playListService.create(dto));
    }

}
