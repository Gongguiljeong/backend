package com.gongguiljeong.domain.gongguiljeong.controller;


import com.gongguiljeong.domain.gongguiljeong.domain.Gongguiljeong;
import com.gongguiljeong.domain.gongguiljeong.domain.GongguiljeongCreateRequest;
import com.gongguiljeong.domain.gongguiljeong.domain.GongguiljeongResponse;
import com.gongguiljeong.domain.gongguiljeong.service.GongguiljeongService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gongguiljeongs")
public class GongguiljeongController {

    private final GongguiljeongService gongguiljeongService;


    @Operation(summary = "공구일정 등록")
    @PostMapping
    public ResponseEntity<?> create(@RequestPart(value = "images") List<MultipartFile> files, @Valid @RequestPart(value = "data") GongguiljeongCreateRequest gongguiljeongCreateRequest) {
        Gongguiljeong gongguiljeong = gongguiljeongService.create(files, gongguiljeongCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(gongguiljeong.getId());
    }


    @Operation(summary = "공구일정 상세보기")
    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        return ResponseEntity.ok(GongguiljeongResponse.from(gongguiljeongService.get(id)));
    }


    @Operation(summary = "공구일정 리스트가져오기")
    @GetMapping
    public ResponseEntity<?> readList(Pageable pageable) {
        return ResponseEntity.ok(gongguiljeongService.getList(pageable).map(GongguiljeongResponse::from));
    }


    @Operation(summary = "공구일정 삭제하기")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        gongguiljeongService.delete(id);
    }


    @Operation(summary = "공구일정 수정하기")
    @PutMapping("/{id}")
    public ResponseEntity<?> update() {
        return ResponseEntity.ok("");
    }
}
