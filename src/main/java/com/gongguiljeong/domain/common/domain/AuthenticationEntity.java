package com.gongguiljeong.domain.common.domain;

import com.gongguiljeong.domain.admin.domain.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
public class AuthenticationEntity {

    private final Long id;
    private final Role role;



    public AuthenticationEntity(Long id, Role role) {
        this.id = id;
        this.role = role;
    }
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getRole().name()));
    }
}
