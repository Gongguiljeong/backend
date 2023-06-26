package com.gongguiljeong.domain.admin.domain;

import com.gongguiljeong.domain.common.domain.BaseEntity;
import com.gongguiljeong.domain.common.domain.exception.ExceptionCode;
import com.gongguiljeong.domain.common.domain.exception.GongguiljeongException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "admins")
public class Admin extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;
    private String name;
    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    @Builder
    private Admin(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public static Admin from(AdminJoinRequest joinRequest, PasswordEncoder passwordEncoder) {
        return Admin.builder().username(joinRequest.getUsername()).password(passwordEncoder.encode(joinRequest.getPassword())).name(joinRequest.getName()).email(joinRequest.getEmail()).build();
    }

    public Role getRole() {
        return Role.ADMIN;
    }

    public void checkPassword(String password, PasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(password, this.password)) {
            throw new GongguiljeongException(ExceptionCode.ADMIN_NOT_FOUND);
        }
    }
}

