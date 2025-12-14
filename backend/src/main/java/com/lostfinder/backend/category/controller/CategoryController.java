package com.lostfinder.backend.category.controller;

import com.lostfinder.backend.category.dto.CategoryResDTO;
import com.lostfinder.backend.category.service.CategoryService;
import com.lostfinder.backend.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ApiResponse<List<CategoryResDTO>> getCategories() {
        return ApiResponse.success(
                categoryService.getCategories(),
                "카테고리 목록 조회 성공"
        );
    }
}
