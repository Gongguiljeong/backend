package com.gongguiljeong.domain.admin.service;

import com.gongguiljeong.domain.admin.domain.Admin;
import com.gongguiljeong.domain.admin.domain.AdminJoinRequest;
import com.gongguiljeong.domain.admin.repository.AdminRepository;
import com.gongguiljeong.domain.common.domain.LoginRequest;
import com.gongguiljeong.domain.common.domain.exception.GongguiljeongException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
@ActiveProfiles("test")
class AdminServiceTest {


    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminService adminService;


    @MockBean
    private BCryptPasswordEncoder passwordEncoder;
    @BeforeEach
    public void cleanup() {
        adminRepository.deleteAllInBatch();
    }

    @DisplayName("admin이 회원가입할 때 중복되는 email이 있으면 에러를 던진다.")
    @Test
    public void joinDuplicateEmail() {
        //given
        Admin admin = createAdmin("kwon1", "kwon1", "kwon1", "kwon1@naver.com");

        adminRepository.save(admin);

        AdminJoinRequest adminJoinRequest = AdminJoinRequest.builder()
                .username("kwon1")
                .password("kwon1")
                .name("kwon1234")
                .email("kwon1@naver.com")
                .build();
        //when
        //then
        assertThatThrownBy(() -> {
            adminService.join(adminJoinRequest);
        }).isInstanceOf(GongguiljeongException.class);
    }



    @DisplayName("admin이 회원가입할 때 중복되는 username이 있으면 에러를 던진다.")
    @Test
    public void joinDuplicateUsername() {
        //given
        Admin admin = createAdmin("kwon1", "kwon1", "kwon1", "kwon1@naver.com");
        adminRepository.save(admin);

        AdminJoinRequest adminJoinRequest = AdminJoinRequest.builder()
                .username("kwon1")
                .password("kwon1")
                .name("kwon1")
                .email("kwon90373@naver.com")
                .build();
        //when
        //then
        assertThatThrownBy(() -> {
            adminService.join(adminJoinRequest);
        }).isInstanceOf(GongguiljeongException.class);
    }

    @DisplayName("admin이 회원가입할 때 중복되는 username과 Email이 없으면 성공한다.")
    @Test
    public void joinSucess() {
        //given
        Admin admin = Admin.builder()
                .username("kwon1")
                .password("kwon1")
                .name("kwon1")
                .email("kwon90371@naver.com")
                .build();


        adminRepository.save(admin);


        AdminJoinRequest adminJoinRequest = AdminJoinRequest.builder()
                .username("gio")
                .password("gio1")
                .name("gio1")
                .email("gio@naver.com")
                .build();

        BDDMockito.when(passwordEncoder.encode(any())).thenReturn("gio1");
        //when
        Admin result = adminService.join(adminJoinRequest);
        //then
        assertThat(result.getUsername()).isEqualTo("gio");
        assertThat(result.getPassword()).isEqualTo("gio1");
        assertThat(result.getName()).isEqualTo("gio1");
        assertThat(result.getEmail()).isEqualTo("gio@naver.com");
    }


    @DisplayName("admin이 로그인 할 때 username과 password가 일치하면 로그인 성공한다.")
    @Test
    public void loginSucess() {
        //given
        Admin admin = Admin.builder()
                .username("kwon1")
                .password("kwon1")
                .name("kwon1")
                .email("kwon90371@naver.com")
                .build();


        adminRepository.save(admin);


        LoginRequest loginRequest = LoginRequest.builder()
                .username("kwon1")
                .password("kwon1")
                .build();

        BDDMockito.when(passwordEncoder.matches(any(), any())).thenReturn(true);
        //when
        Admin result = adminService.login(loginRequest);
        //then
        assertThat(result.getUsername()).isEqualTo("kwon1");
        assertThat(result.getPassword()).isEqualTo("kwon1");
        assertThat(result.getName()).isEqualTo("kwon1");
        assertThat(result.getEmail()).isEqualTo("kwon90371@naver.com");
    }



    @DisplayName("admin이 로그인 할 때 username이 존재하지 않으면 에러를 던진다.")
    @Test
    public void loginFail1() {
        //given
        Admin admin = Admin.builder()
                .username("kwon1")
                .password("kwon1")
                .name("kwon1")
                .email("kwon90371@naver.com")
                .build();


        adminRepository.save(admin);


        LoginRequest loginRequest = LoginRequest.builder()
                .username("gio1")
                .password("gio1")
                .build();

        BDDMockito.when(passwordEncoder.matches(any(), any())).thenReturn(true);
        //when
        //then
        assertThatThrownBy(() -> {
            adminService.login(loginRequest);
        }).isInstanceOf(GongguiljeongException.class);

    }


    @DisplayName("admin이 로그인 할 때 username은 존재하고, password가 다르면 에러를 던진다.")
    @Test
    public void loginFail2() {
        //given
        Admin admin = Admin.builder()
                .username("kwon1")
                .password("kwon1")
                .name("kwon1")
                .email("kwon90371@naver.com")
                .build();


        adminRepository.save(admin);

        LoginRequest loginRequest = LoginRequest.builder()
                .username("kwon1")
                .password("asdf")
                .build();

        BDDMockito.when(passwordEncoder.matches(any(), any())).thenReturn(false);
        //when
        //then
        assertThatThrownBy(() -> {
            adminService.login(loginRequest);
        }).isInstanceOf(GongguiljeongException.class);
    }




    public static Admin createAdmin(String username, String password, String name, String email) {
        return Admin.builder()
                .username(username)
                .password(password)
                .name(name)
                .email(email)
                .build();
    }
}