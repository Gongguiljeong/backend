package com.gongguiljeong.domain.admin.controller.response;

import com.gongguiljeong.domain.admin.domain.Admin;
import com.gongguiljeong.domain.admin.domain.Role;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdminResponse {
    private final String name;
    private final String username;
    private final String email;
    private final Role role;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;


    @Builder
    private AdminResponse(String name, String username, String email, Role role, LocalDateTime createDate, LocalDateTime updateDate) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.role = role;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public static AdminResponse from(Admin admin) {
        return AdminResponse.builder()
                .name(admin.getName())
                .username(admin.getUsername())
                .email(admin.getEmail())
                .role(admin.getRole())
                .createDate(admin.getCreateDate())
                .updateDate(admin.getUpdateDate())
                .build();
    }
}
