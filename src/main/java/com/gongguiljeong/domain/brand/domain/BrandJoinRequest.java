package com.gongguiljeong.domain.brand.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BrandJoinRequest {
    private String name;
    private String username;
    private String password;
    private String email;
    private String link;

    @Builder
    private BrandJoinRequest(String name, String username, String password, String email, String link) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.link = link;
    }

    public Brand toEntity() {
        return Brand.builder()
                .name(name)
                .username(username)
                .password(password)
                .email(email)
                .link(link)
                .build();
    }
}
