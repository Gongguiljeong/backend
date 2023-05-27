package com.gongguiljeong.domain.admin.model;

import com.gongguiljeong.domain.brand.model.Brand;
import com.gongguiljeong.global.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Admin extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private String refreshToken;

    private String accessToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() { // 로그인 아이디
        return loginId;
    }

    @Override
    public String getPassword() { // 비밀번호
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {// 계정이 만료되었는지
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {// 계정이 잠겨있는지
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 비밀번호가 만료되었는지
        return true;
    }

    @Override
    public boolean isEnabled() { // 계정이 활성화 되어있는지
        return true;
    }
}

