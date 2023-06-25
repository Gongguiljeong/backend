package com.gongguiljeong.domain.category.controller;


import com.gongguiljeong.domain.category.domain.MainCategoryCreateRequest;
import com.gongguiljeong.domain.category.service.MainCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mainCategories")
public class MainCategoryController {
    private final MainCategoryService mainCategoryService;

    @PostMapping("")
    public ResponseEntity<?> createMainCategory(@Valid @RequestBody MainCategoryCreateRequest mainCategoryCreateRequest) {
        mainCategoryService.createMainCategory(mainCategoryCreateRequest);
        return ResponseEntity.ok("");
    }
}
