package com.gongguiljeong.domain.brand.domain;

import com.gongguiljeong.domain.admin.domain.Role;
import com.gongguiljeong.domain.common.domain.BaseEntity;
import com.gongguiljeong.domain.common.domain.exception.ExceptionCode;
import com.gongguiljeong.domain.common.domain.exception.GongguiljeongException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "brands")
public class Brand extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long id;
    private String name;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private String link;


    @Builder
    private Brand(Long id, String name, String username, String password, String email, String link) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.link = link;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Role.BRAND.name()));
    }


    public Role getRole() {
        return Role.BRAND;
    }

    public void passwordValid(String loginPassword, PasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(loginPassword, password)) {
            throw new GongguiljeongException(ExceptionCode.BRAND_NOT_FOUND);
        }
    }
}

