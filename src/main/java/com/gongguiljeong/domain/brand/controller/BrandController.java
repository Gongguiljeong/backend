package com.gongguiljeong.domain.brand.controller;


import com.gongguiljeong.domain.brand.domain.*;
import com.gongguiljeong.domain.brand.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RestController
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    @Operation(summary = "브랜드 회원가입")
    @PostMapping
    public ResponseEntity<?> join(@Valid @RequestBody BrandJoinRequest brandJoinRequest) {
        brandService.join(brandJoinRequest);
        return ResponseEntity.status(CREATED).body("브랜드 회원가입 성공");
    }

    @Operation(summary = "브랜드 하나 읽기")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(BrandResponse.from(brandService.read(id)));
    }


}
