package com.gongguiljeong.domain.admin.domain;


import com.gongguiljeong.domain.common.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@NoArgsConstructor
public class LoginRequest {

    private Long id;
    private String username;
    private String password;

    public LoginRequest(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
