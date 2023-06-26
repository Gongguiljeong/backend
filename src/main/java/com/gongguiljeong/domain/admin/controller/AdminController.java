package com.gongguiljeong.domain.admin.controller;


import com.gongguiljeong.domain.admin.domain.AdminJoinRequest;
import com.gongguiljeong.domain.admin.controller.response.AdminResponse;
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

    @PostMapping
    public ResponseEntity<?> join(@Valid @RequestBody AdminJoinRequest adminJoinRequest) {
        return ResponseEntity.ok(AdminResponse.from(adminService.join(adminJoinRequest)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        return ResponseEntity.ok(AdminResponse.from(adminService.read(id)));
    }

    @GetMapping
    public ResponseEntity<?> readList(Pageable pageable) {
        return ResponseEntity.ok(adminService.readList(pageable).map(AdminResponse::from));
    }
}
