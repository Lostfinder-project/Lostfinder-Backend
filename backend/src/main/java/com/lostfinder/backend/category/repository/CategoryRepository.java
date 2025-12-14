package com.lostfinder.backend.category.repository;

import com.lostfinder.backend.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById(Long id);
}
