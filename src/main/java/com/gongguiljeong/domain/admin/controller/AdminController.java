package com.gongguiljeong.domain.admin.controller;


import com.gongguiljeong.domain.admin.domain.AdminJoinRequest;
import com.gongguiljeong.domain.admin.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("")
    public ResponseEntity<?> join(@Valid @RequestBody AdminJoinRequest adminJoinRequest) {
        adminService.join(adminJoinRequest);
        return ResponseEntity.ok("회원가입완료");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readAdmin(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.readAdmin(id));
    }

    @GetMapping("")
    public ResponseEntity<?> readAdminList(Pageable pageable) {
        return ResponseEntity.ok(adminService.readAdminList(pageable));
    }
}
