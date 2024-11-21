package com.youtube.service;

import com.youtube.dto.CategoryDTO;
import com.youtube.entity.CategoryEntity;
import com.youtube.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public  void  createCategory(CategoryDTO categoryDTO) {
        if (categoryDTO.getName() == null || categoryDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Tag name cannot be null or empty");
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryDTO.getName());
        categoryEntity.setCreatedDate(LocalDateTime.now());
        categoryRepository.save(categoryEntity);
    }
    public CategoryDTO updateCategory(Integer id , CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag with ID " + id + " not found"));

        categoryEntity.setName(categoryDTO.getName());
        categoryEntity.setCreatedDate(LocalDateTime.now());
        categoryRepository.save(categoryEntity);
        categoryDTO.setId(categoryEntity.getId());
        return categoryDTO;
    }

    public void deleteCategory(Integer id) {
        Optional<CategoryEntity> entity=categoryRepository.findById(id);
        if(entity.isPresent()) {
            categoryRepository.delete(entity.get());
        }else {
            throw new EntityNotFoundException("Tag with ID " + id + " not found");
        }
    }
    public List<CategoryDTO> getAllTag() {
        List<CategoryEntity> entityList= (List<CategoryEntity>) categoryRepository.findAll();

        List<CategoryDTO>  categoryDTOS=new ArrayList<>();

        for (CategoryEntity categoryEntity : entityList) {
              CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(categoryEntity.getId());
            categoryDTO.setName(categoryEntity.getName());
            categoryDTO.setCreatedDate(categoryEntity.getCreatedDate());
            categoryDTOS.add(categoryDTO);
        }
        return  categoryDTOS;
    }
}
