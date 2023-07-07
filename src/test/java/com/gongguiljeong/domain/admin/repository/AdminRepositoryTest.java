//package com.gongguiljeong.domain.admin.repository;
//
//import com.gongguiljeong.domain.admin.domain.Admin;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@ActiveProfiles("test")
//class AdminRepositoryTest {
//
//    @Autowired
//    private AdminRepository adminRepository;
//
//    @AfterEach
//    public void cleanUp() {
//        adminRepository.deleteAllInBatch();
//    }
//
//    @DisplayName("username과 email을 가지고 있는 admin이 있는지 확인한다.")
//    @Test
//    void existsByUsername() {
//        //given
//        Admin admin = Admin.builder()
//                .username("kwon1")
//                .password("kwon1")
//                .name("kwon")
//                .email("kwon9037@naver.com")
//                .build();
//        adminRepository.save(admin);
//        //when
//        boolean result = adminRepository.existsByUsernameOrEmail("kwon1", "kwon9037@naver.com");
//
//        //then
//        assertThat(result).isEqualTo(true);
//    }
//
//
//    @DisplayName("username으로 admin을 찾는다.")
//    @Test
//    void findByUsername() {
//        //given
//        Admin admin = Admin.builder()
//                .username("kwon1")
//                .password("kwon1")
//                .name("kwon")
//                .email("kwon9037@naver.com")
//                .build();
//        adminRepository.save(admin);
//        //when
//        Optional<Admin> kwon1 = adminRepository.findByUsername("kwon1");
//
//        //then
//        assertThat(kwon1.get().getUsername()).isEqualTo("kwon1");
//    }
//}