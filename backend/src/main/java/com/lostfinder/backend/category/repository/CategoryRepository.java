package com.lostfinder.backend.category.repository;

import com.lostfinder.backend.category.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findById(Long id);
}
