package com.gongguiljeong.domain.gongguiljeong.controller;


import com.gongguiljeong.domain.admin.model.Admin;
import com.gongguiljeong.domain.gongguiljeong.dto.GongguiljeongCreateRequest;
import com.gongguiljeong.domain.gongguiljeong.service.GongguiljeongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gongguiljeongs")
public class GongguiljeongController {

    private final GongguiljeongService gongguiljeongService;


    @PostMapping("")
    public ResponseEntity<?> createGongguiljeong(@RequestPart(value = "images") List<MultipartFile> files, @Valid @RequestPart(value = "Gongguiljeong") GongguiljeongCreateRequest gongguiljeongCreateRequest) {
        gongguiljeongService.createGongguiljeong(files, gongguiljeongCreateRequest);
        return ResponseEntity.ok("");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readGongguiljeong() {
        return ResponseEntity.ok("");
    }

    @GetMapping("")
    public ResponseEntity<?> readGongguiljeongList() {
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGongguiljeong() {
        return ResponseEntity.ok("");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGongguiljeong() {
        return ResponseEntity.ok("");
    }
}
