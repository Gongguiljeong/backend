//package com.gongguiljeong.domain.admin.controller.response;
//
//import com.gongguiljeong.domain.admin.domain.Admin;
//import com.gongguiljeong.domain.admin.domain.Role;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.test.context.ActiveProfiles;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ActiveProfiles("test")
//class AdminResponseTest {
//
//
//    @DisplayName("admind으로 adminResponse를 생성할 수 있다.")
//    @Test
//    public void createAdminResponse(){
//        //given
//        Admin admin = Admin.builder()
//                .name("kwon")
//                .email("kwon@naver.com")
//                .password("kwon")
//                .username("kwon")
//                .build();
//        //when
//        AdminResponse result = AdminResponse.from(admin);
//        //then
//
//        assertThat(result.getUsername()).isEqualTo("kwon");
//        assertThat(result.getEmail()).isEqualTo("kwon@naver.com");
//        assertThat(result.getRole()).isEqualTo(Role.ADMIN);
//        assertThat(result.getName()).isEqualTo("kwon");
//    }
//
//}