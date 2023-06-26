package com.gongguiljeong.domain.category.controller;


import com.gongguiljeong.domain.category.domain.SubCategoryCreateRequest;
import com.gongguiljeong.domain.category.service.SubCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/subCategories")
public class SubCategoryController {
    private final SubCategoryService subcategoryService;

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody SubCategoryCreateRequest subCategoryCreateRequest) {
        subcategoryService.createSubCategory(subCategoryCreateRequest);
        return ResponseEntity.ok("");
    }
}
