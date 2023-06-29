package com.gongguiljeong.domain.gongguiljeong.controller;


import com.gongguiljeong.domain.gongguiljeong.domain.Gongguiljeong;
import com.gongguiljeong.domain.gongguiljeong.domain.GongguiljeongCreateRequest;
import com.gongguiljeong.domain.gongguiljeong.domain.GongguiljeongResponse;
import com.gongguiljeong.domain.gongguiljeong.domain.GongguiljeongSearchCondition;
import com.gongguiljeong.domain.gongguiljeong.service.GongguiljeongService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gongguiljeongs")
@Slf4j
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
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(GongguiljeongResponse.from(gongguiljeongService.get(id)));
    }


    @Operation(summary = "메인화면 공구일정 3개 가져오기")
    @GetMapping
    public ResponseEntity<?> getList(@PageableDefault(size = 3) @SortDefault.SortDefaults({
            @SortDefault(sort ="closeDate" ,direction = Sort.Direction.DESC),
            @SortDefault(sort ="interestCount" ,direction = Sort.Direction.DESC),
            @SortDefault(sort ="title" ,direction = Sort.Direction.ASC),
    }) Pageable pageable) {
        return ResponseEntity.ok(gongguiljeongService.getList(pageable).map(GongguiljeongResponse::from));
    }


    @Operation(summary = "공구일정 검색 페이지 관심공구 수가 높은 수대로 10개 출력")
    @GetMapping("/hot")
    public ResponseEntity<?> getHotGongguiljeong(@PageableDefault(size = 10) @SortDefault.SortDefaults({
            @SortDefault(sort ="interestCount" ,direction = Sort.Direction.DESC),
            @SortDefault(sort ="closeDate" ,direction = Sort.Direction.DESC),
            @SortDefault(sort ="title" ,direction = Sort.Direction.ASC),
    }) Pageable pageable) {
        return ResponseEntity.ok(gongguiljeongService.getList(pageable).map(GongguiljeongResponse::from));
    }


    @Operation(summary = "공구일정 타이틀로 검색")
    @GetMapping("/search")
    public ResponseEntity<?> search(@PageableDefault(size = 20) @SortDefault.SortDefaults({
            @SortDefault(sort ="openDate" ,direction = Sort.Direction.ASC),
            @SortDefault(sort ="closeDate" ,direction = Sort.Direction.ASC),
            @SortDefault(sort ="interestCount" ,direction = Sort.Direction.ASC),
            @SortDefault(sort ="title" ,direction = Sort.Direction.ASC),
    }) Pageable pageable,  @RequestParam(value = "title") String title) {
        log.info("검색 title : {}", title);
        return ResponseEntity.ok(gongguiljeongService.getSearchList(pageable, title).map(GongguiljeongResponse::from));
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
