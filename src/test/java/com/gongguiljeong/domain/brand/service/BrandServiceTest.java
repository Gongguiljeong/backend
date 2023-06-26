package com.gongguiljeong.domain.brand.service;

import com.gongguiljeong.domain.common.domain.LoginRequest;
import com.gongguiljeong.domain.brand.domain.Brand;
import com.gongguiljeong.domain.brand.domain.BrandJoinRequest;
import com.gongguiljeong.domain.brand.repository.BrandRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
@ActiveProfiles("test")
class BrandServiceTest {

    @Autowired
    private BrandService brandService;

    @Autowired
    private BrandRepository brandRepository;
    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    @AfterEach
    public void afterEach() {
        brandRepository.deleteAllInBatch();
    }


    @DisplayName("brandJoinRequest로 brand 회원가입을 할 수 있다.")
    @Test
    public void join() {
        //given
        BrandJoinRequest brandJoinRequest = BrandJoinRequest.builder().
                username("kwon1")
                .password("kwon2")
                .name("kwongio")
                .email("rldh9037@naver.com")
                .link("www.naver.com")
                .build();
        //when
        BDDMockito.when(passwordEncoder.encode(any())).thenReturn("kwon2");
        Brand brand = brandService.join(brandJoinRequest);

        //then
        assertThat(brand.getUsername()).isEqualTo("kwon1");
        assertThat(brand.getPassword()).isEqualTo("kwon2");
        assertThat(brand.getName()).isEqualTo("kwongio");
        assertThat(brand.getEmail()).isEqualTo("rldh9037@naver.com");
        assertThat(brand.getLink()).isEqualTo("www.naver.com");
    }

    @Test
    public void read() {
        //given
        //when
        //then
    }

    @DisplayName("brandLoginRequest로 brand 로그인을 할 수 있다.")
    @Test
    public void login() {
        //given
        Brand brand = Brand.builder()
                .username("kwon1")
                .password("kwon2")
                .name("kwongio")
                .email("rldh9037@naver.com")
                .link("www.naver.com")
                .build();

        brandRepository.save(brand);

        LoginRequest loginRequest = LoginRequest.builder()
                .username("kwon1")
                .password("kwon2")
                .build();

        BDDMockito.when(passwordEncoder.matches(any(), any())).thenReturn(true);
        //when
        Brand result = brandService.login(loginRequest);
        //then
        assertThat(result.getUsername()).isEqualTo("kwon1");
        assertThat(result.getPassword()).isEqualTo("kwon2");


    }

}