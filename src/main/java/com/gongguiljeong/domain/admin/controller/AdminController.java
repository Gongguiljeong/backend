package com.gongguiljeong.domain.admin.controller;


import com.gongguiljeong.domain.admin.controller.response.GongguiljeongUpdateResponse;
import com.gongguiljeong.domain.admin.domain.AdminJoinRequest;
import com.gongguiljeong.domain.admin.controller.response.AdminResponse;
import com.gongguiljeong.domain.admin.service.AdminService;
import com.gongguiljeong.domain.common.domain.AuthenticationEntity;
import com.gongguiljeong.domain.gongguiljeong.controller.response.GongguiljeongCreateResponse;
import com.gongguiljeong.domain.gongguiljeong.domain.Gongguiljeong;
import com.gongguiljeong.domain.gongguiljeong.domain.request.GongguiljeongCreateRequest;
import com.gongguiljeong.domain.gongguiljeong.domain.request.GongguiljeongUpdateRequest;
import com.gongguiljeong.domain.gongguiljeong.service.GongguiljeongService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminController {
    private final AdminService adminService;

    private final GongguiljeongService gongguiljeongService;


    @Operation(summary = "어드민 공구일정 등록하기")
    @PostMapping("/gongguiljeongs")
    public ResponseEntity<?> createGongguijeong(@AuthenticationPrincipal AuthenticationEntity authenticationEntity, @RequestPart(value = "images") List<MultipartFile> files, @Valid @RequestPart(value = "data") GongguiljeongCreateRequest gongguiljeongCreateRequest) {
        Gongguiljeong gongguiljeong = gongguiljeongService.create(files, gongguiljeongCreateRequest, authenticationEntity.getId());
        return ResponseEntity.status(CREATED).body(GongguiljeongCreateResponse.from(gongguiljeong));
    }
    @Operation(summary = "공구일정 삭제하기")
    @DeleteMapping("/{id}")
    public void deleteGongguiljeong(@PathVariable Long id) {
        gongguiljeongService.delete(id);
    }

    @Operation(summary = "공구일정 수정하기")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateGongguiljeong(@PathVariable Long id, @Valid @RequestBody GongguiljeongUpdateRequest gongguiljeongUpdateRequest) {
        return ResponseEntity.ok(GongguiljeongUpdateResponse.from(gongguiljeongService.update(gongguiljeongUpdateRequest, id)));
    }

    @Operation(summary = "어드민 회원가입")
    @PostMapping
    public ResponseEntity<?> join(@Valid @RequestBody AdminJoinRequest adminJoinRequest) {
        return ResponseEntity.status(CREATED).body(AdminResponse.from(adminService.join(adminJoinRequest)));
    }


//    @Operation(summary = "어드민 읽기")
//    @GetMapping("/{id}")
//    public ResponseEntity<?> get(@PathVariable Long id) {
//        return ResponseEntity.ok(AdminResponse.from(adminService.read(id)));
//    }
//
//
//    @Operation(summary = "어드민 회원가입")
//    @GetMapping
//    public ResponseEntity<?> getList(Pageable pageable) {
//        return ResponseEntity.ok(adminService.readList(pageable).map(AdminResponse::from));
//    }
}
