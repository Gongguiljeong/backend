package com.gongguiljeong.domain.gongguiljeong.controller;


import com.gongguiljeong.domain.gongguiljeong.domain.GongguiljeongCreateRequest;
import com.gongguiljeong.domain.gongguiljeong.domain.GongguiljeongResponse;
import com.gongguiljeong.domain.gongguiljeong.service.GongguiljeongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gongguiljeongs")
public class GongguiljeongController {

    private final GongguiljeongService gongguiljeongService;


    @PostMapping("")
    public ResponseEntity<?> createGongguiljeong(@RequestPart(value = "images") List<MultipartFile> files, @Valid @RequestPart(value = "data") GongguiljeongCreateRequest gongguiljeongCreateRequest) {
        gongguiljeongService.createGongguiljeong(files, gongguiljeongCreateRequest);
        return ResponseEntity.ok("공구일정 등록완료");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readGongguiljeong(@PathVariable Long id) {
        GongguiljeongResponse gongguiljeongResponse = gongguiljeongService.readGongguiljeong(id);
        return ResponseEntity.ok(gongguiljeongResponse);
    }

    @GetMapping("")
    public ResponseEntity<?> readGongguiljeongList(Pageable pageable) {
        Page<GongguiljeongResponse> gongguiljeongResponses = gongguiljeongService.readGongguiljeongList(pageable);
        return ResponseEntity.ok(gongguiljeongResponses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGongguiljeong(@PathVariable Long id) {
        gongguiljeongService.deleteGongguiljeong(id);
        return ResponseEntity.ok("");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGongguiljeong() {
        return ResponseEntity.ok("");
    }
}
