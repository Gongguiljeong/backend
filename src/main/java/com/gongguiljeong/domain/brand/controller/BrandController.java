package com.gongguiljeong.domain.brand.controller;


import com.gongguiljeong.domain.brand.dto.BrandCreateRequest;
import com.gongguiljeong.domain.brand.dto.BrandResponse;
import com.gongguiljeong.domain.brand.dto.BrandSearchCondition;
import com.gongguiljeong.domain.brand.dto.BrandUpdateRequest;
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

    @Operation(summary = "브랜드 생성")
    @PostMapping("")
    public ResponseEntity<?> createBrand(@Valid @RequestBody BrandCreateRequest brandCreateRequest) {
        brandService.createBrand(brandCreateRequest);
        return ResponseEntity.ok("브랜드 생성완료");
    }

    @Operation(summary = "브랜드 하나 읽기")
    @GetMapping("/{id}")
    public ResponseEntity<?> readBrand(@PathVariable Long id) {
        BrandResponse brandResponse = brandService.readBrand(id);
        return ResponseEntity.ok(brandResponse);
    }

    @Operation(summary = "브랜드 여러개 읽기 ", description = "브랜드 리스트 가져오기")
    @GetMapping("")
    public ResponseEntity<?> readBrandList(@Valid @RequestBody(required = false) BrandSearchCondition brandSearchCondition, Pageable pageable) {
        Page<BrandResponse> brandList = brandService.readBrandList(brandSearchCondition, pageable);
        return ResponseEntity.ok(brandList);
    }

//    @Operation(summary = "브랜드 삭제하기")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteBrand(@PathVariable Long id) {
//        brandService.deleteBrand(id);
//        return ResponseEntity.ok("브랜드 삭제완료");
//    }
//
//    @Operation(summary = "브랜드 복구하기")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> restoreBrand(@PathVariable Long id) {
//        brandService.restoreBrand(id);
//        return ResponseEntity.ok("브랜드 삭제완료");
//    }

    @Operation(summary = "브랜드 업데이트")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBrand(@PathVariable Long id, @Valid @RequestBody BrandUpdateRequest brandUpdateRequest) {
        brandService.updateBrand(id, brandUpdateRequest);
        return ResponseEntity.ok("브랜드 업데이트 완료");
    }
}
