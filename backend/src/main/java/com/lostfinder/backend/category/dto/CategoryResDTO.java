package com.lostfinder.backend.category.dto;

import com.lostfinder.backend.category.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryResDTO {

    private Long id;
    private String name;

    public static CategoryResDTO from(Category category) {
        return new CategoryResDTO(category.getId(), category.getName());
    }
}
