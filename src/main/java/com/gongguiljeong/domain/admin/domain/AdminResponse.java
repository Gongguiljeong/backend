package com.gongguiljeong.domain.admin.domain;

import com.gongguiljeong.domain.admin.domain.Admin;
import com.gongguiljeong.domain.admin.domain.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class AdminResponse {
    private final String name;
    private final String username;
    private final String email;
    private final Role role;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    public AdminResponse(Admin admin) {
        this.name = admin.getName();
        this.username = admin.getUsername();
        this.email = admin.getEmail();
        this.role = admin.getRole();
        this.createDate = admin.getCreateDate();
        this.updateDate = admin.getUpdateDate();
    }
}
