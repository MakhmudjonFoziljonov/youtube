package com.youtube.category.controller;

import com.youtube.category.dto.CategoryDTO;
import com.youtube.category.service.CategoryService;
import com.youtube.tag.dto.TagDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CategoryController {
     private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping("/create")
//    @PreAuthorize("hasRole('ADMIN')")
    public void createCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.createCategory(categoryDTO);
    }

    @PutMapping("/update/{id}")
    //    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer id,@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updatedCategory=categoryService.updateCategory(id, categoryDTO);
         return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }
    @DeleteMapping("/delete{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTags() {
        try {
            List<CategoryDTO> categoryDTOS = categoryService.getAllTag();
            return ResponseEntity.ok(categoryDTOS);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while fetching tags: " + e.getMessage());
        }
    }


}
