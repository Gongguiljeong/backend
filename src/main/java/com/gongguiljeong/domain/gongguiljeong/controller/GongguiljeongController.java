package com.gongguiljeong.domain.gongguiljeong.controller;


import com.gongguiljeong.domain.gongguiljeong.controller.response.GongguiljeongDetailResponse;
import com.gongguiljeong.domain.gongguiljeong.controller.response.GongguiljeongResponse;
import com.gongguiljeong.domain.gongguiljeong.service.GongguiljeongService;
import com.gongguiljeong.domain.image.domain.SubImage;
import com.gongguiljeong.domain.image.service.SubImageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gongguiljeongs")
@Slf4j
public class GongguiljeongController {

    private final GongguiljeongService gongguiljeongService;
    private final SubImageService subImageService;

    @Operation(summary = "공구일정 상세보기")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        List<SubImage> subImages = subImageService.getByGonggiljeongId(id);
        return ResponseEntity.ok(GongguiljeongDetailResponse.from(gongguiljeongService.get(id), subImages));
    }

    @Operation(summary = "메인화면 공구일정 3개 가져오기")
    @GetMapping
    public ResponseEntity<?> getList(@PageableDefault(size = 3) @SortDefault.SortDefaults({
            @SortDefault(sort = "closeDate", direction = Sort.Direction.DESC),
            @SortDefault(sort = "interestCount", direction = Sort.Direction.DESC),
            @SortDefault(sort = "title", direction = Sort.Direction.ASC),
    }) Pageable pageable) {
        return ResponseEntity.ok(gongguiljeongService.getList(pageable).map(GongguiljeongResponse::from));
    }

    @Operation(summary = "카테고리로 공구일정을 리스트를  가져온다.")
    @GetMapping("/category")
    public ResponseEntity<?> category(@PageableDefault(size = 20) @SortDefault.SortDefaults({
            @SortDefault(sort = "closeDate", direction = Sort.Direction.DESC),
            @SortDefault(sort = "interestCount", direction = Sort.Direction.DESC),
            @SortDefault(sort = "title", direction = Sort.Direction.ASC)
    }) Pageable pageable, @RequestParam(required = false) Long mainCategoryId, @RequestParam(required = false) Long subCategoryId) {
        return ResponseEntity.ok(gongguiljeongService.findByCategoryId(pageable, mainCategoryId, subCategoryId).map(GongguiljeongResponse::from));
    }


    @Operation(summary = "공구일정 검색 페이지 관심공구 수가 높은 수대로 10개 출력")
    @GetMapping("/hot")
    public ResponseEntity<?> hot(@PageableDefault(size = 10) @SortDefault.SortDefaults({
            @SortDefault(sort = "interestCount", direction = Sort.Direction.DESC),
            @SortDefault(sort = "closeDate", direction = Sort.Direction.DESC),
            @SortDefault(sort = "title", direction = Sort.Direction.ASC),
    }) Pageable pageable) {
        return ResponseEntity.ok(gongguiljeongService.getList(pageable).map(GongguiljeongResponse::from));
    }

    @Operation(summary = "공구일정 타이틀로 검색")
    @GetMapping("/search")
    public ResponseEntity<?> search(@PageableDefault(size = 20) @SortDefault.SortDefaults({
            @SortDefault(sort = "openDate", direction = Sort.Direction.ASC),
            @SortDefault(sort = "closeDate", direction = Sort.Direction.ASC),
            @SortDefault(sort = "interestCount", direction = Sort.Direction.ASC),
            @SortDefault(sort = "title", direction = Sort.Direction.ASC),
    }) Pageable pageable, @RequestParam(value = "title") String title) {
        log.debug("검색 title : {}", title);
        return ResponseEntity.ok(gongguiljeongService.findByTitle(pageable, title).map(GongguiljeongResponse::from));
    }


}
