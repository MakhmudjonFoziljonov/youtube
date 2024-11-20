package com.youtube.tag.service;

import com.youtube.tag.dto.TagDTO;
import com.youtube.tag.entity.TagEntity;
import com.youtube.tag.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
    public void create(TagDTO tagDTO) {
        if (tagDTO.getName() == null || tagDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Tag name cannot be null or empty");
        }
        TagEntity tagEntity = new TagEntity();
        tagEntity.setName(tagDTO.getName());
        tagEntity.setCreated(LocalDateTime.now());
        tagRepository.save(tagEntity);
    }

    public TagEntity update(Integer id, TagDTO tagDTO) {
        TagEntity entity = tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag with ID " + id + " not found"));

        entity.setName(tagDTO.getName());
        entity.setCreated(LocalDateTime.now());
        return tagRepository.save(entity);
    }

    public void delete(Integer id) {
        Optional<TagEntity> entity=tagRepository.findById(id);
        if(entity.isPresent()) {
            tagRepository.delete(entity.get());
        }else {
            throw new EntityNotFoundException("Tag with ID " + id + " not found");
        }
    }

    public  List<TagDTO> getAllTags() {
        List<TagEntity> entityList= (List<TagEntity>) tagRepository.findAll();

        List<TagDTO> tagDTOList=new ArrayList<>();

        for (TagEntity tagEntity : entityList) {
            TagDTO tagDTO = new TagDTO();

            tagDTO.setId(tagEntity.getId());
            tagDTO.setName(tagEntity.getName());
            tagDTO.setCreatedate(tagEntity.getCreated());
            tagDTOList.add(tagDTO);
        }
        return  tagDTOList;
    }

}
