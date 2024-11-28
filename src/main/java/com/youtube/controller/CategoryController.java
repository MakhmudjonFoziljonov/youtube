package com.youtube.controller;

import com.youtube.dto.CategoryDTO;
import com.youtube.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Create a new category",
            description = "Allows an admin to create a new category. Requires category details in the request body.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public void createCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.createCategory(categoryDTO);
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Update a category",
            description = "Updates an existing category by its ID. Requires updated category details in the request body."
    )
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updatedCategory = categoryService.updateCategory(id, categoryDTO);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/delete{id}")
    @Operation(
            summary = "Delete a category",
            description = "Deletes an existing category by its ID."
    )
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get all categories",
            description = "Fetches a list of all available categories."
    )
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
