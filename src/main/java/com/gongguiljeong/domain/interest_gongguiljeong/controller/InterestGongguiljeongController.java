package com.gongguiljeong.domain.interest_gongguiljeong.controller;


import com.gongguiljeong.domain.common.domain.AuthenticationEntity;
import com.gongguiljeong.domain.interest_gongguiljeong.domain.InterestGongguiljeong;
import com.gongguiljeong.domain.interest_gongguiljeong.service.InterestGongguiljeongService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interest")
public class InterestGongguiljeongController {

    private final InterestGongguiljeongService interestGongguiljeongService;

    @Operation(summary = "관심공구 등록하기")
    @PostMapping("/{gongguiljeongId}")
    public ResponseEntity<?> register(@AuthenticationPrincipal AuthenticationEntity user, @PathVariable Long gongguiljeongId) {
        InterestGongguiljeong interestGongguiljeong = interestGongguiljeongService.register(user.getId(), gongguiljeongId);
        return ResponseEntity.status(CREATED).body(InterestGongguiljeongCreateResponse.from(interestGongguiljeong));
    }

    @Operation(summary = "관심공구 삭제하기")
    @DeleteMapping("/{interestId}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal AuthenticationEntity user, @PathVariable Long interestId) {
        InterestGongguiljeong interestGongguiljeong = interestGongguiljeongService.delete(user.getId(), interestId);
        return ResponseEntity.ok(InterestGongguiljeongDeleteResponse.from(interestGongguiljeong));
    }

    @Operation(summary = "내 관심공구 리스트 가져오기")
    @GetMapping
    public ResponseEntity<?> getList(@AuthenticationPrincipal AuthenticationEntity user, @PageableDefault(size = 10) @SortDefault.SortDefaults({
            @SortDefault(sort = "openDate", direction = Sort.Direction.ASC),
            @SortDefault(sort = "closeDate", direction = Sort.Direction.ASC),
            @SortDefault(sort = "interestCount", direction = Sort.Direction.ASC),
            @SortDefault(sort = "title", direction = Sort.Direction.ASC),
    }) Pageable pageable) {
        return ResponseEntity.ok(interestGongguiljeongService.findByUserId(user.getId(), pageable).map(InterestGongguiljeongReadResponse::from));
    }

    @Operation(summary = "카테고리로 내 관심공구 리스트를 가져온다.")
    @GetMapping("/category")
    public ResponseEntity<?> category(@AuthenticationPrincipal AuthenticationEntity user, @PageableDefault(size = 20) @SortDefault.SortDefaults({
            @SortDefault(sort = "closeDate", direction = Sort.Direction.DESC),
            @SortDefault(sort = "interestCount", direction = Sort.Direction.DESC),
            @SortDefault(sort = "title", direction = Sort.Direction.ASC)
    }) Pageable pageable, @RequestParam(required = false) Long mainCategoryId, @RequestParam(required = false) Long subCategoryId) {
        return ResponseEntity.ok(interestGongguiljeongService.findByCategoryId(pageable,mainCategoryId,subCategoryId));
    }

}
