package com.gongguiljeong.domain.brand.controller;


import com.gongguiljeong.domain.brand.domain.*;
import com.gongguiljeong.domain.brand.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    @Operation(summary = "브랜드 회원가입")
    @PostMapping("")
    public ResponseEntity<?> join(@Valid @RequestBody BrandJoinRequest brandJoinRequest) {
        brandService.join(brandJoinRequest);
        return ResponseEntity.ok("브랜드 생성완료");
    }

    @Operation(summary = "브랜드 하나 읽기")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(BrandResponse.from(brandService.readBrand(id)));
    }


}
