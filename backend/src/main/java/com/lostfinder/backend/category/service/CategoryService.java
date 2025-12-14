package com.lostfinder.backend.category.service;

import com.lostfinder.backend.category.domain.Category;
import com.lostfinder.backend.category.dto.CategoryResDTO;
import com.lostfinder.backend.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResDTO> getCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryResDTO::from)
                .toList();
    }
}
