package com.gongguiljeong.domain.admin.controller;


import com.gongguiljeong.domain.admin.dto.AdminJoinRequest;
import com.gongguiljeong.domain.admin.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("")
    public ResponseEntity<?> join(@Valid @RequestBody AdminJoinRequest adminJoinRequest) {
        adminService.join(adminJoinRequest);
        return ResponseEntity.ok("회원가입완료");
    }
}
