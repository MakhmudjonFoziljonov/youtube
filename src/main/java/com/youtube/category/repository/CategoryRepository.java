package com.youtube.category.repository;

import com.youtube.category.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity,Integer> {
}
