package com.gongguiljeong.global.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc //Mock 환경에 MockMvc가 등록됨
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class SecurityConfigTest {


    //가짜 환경에 등록된 MockMvc를 DI함
    @Autowired
    private MockMvc mockMvc;

//    @DisplayName("인증 테스트")
//    @Test
//    public void authenticationTest() throws Exception {
//        ResultActions resultActions = mockMvc.perform(get("/brands"));
//        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
//        int status = resultActions.andReturn().getResponse().getStatus();
//        System.out.println("테스트: " + responseBody);
//        System.out.println("상태: " + status);
//        Assertions.assertThat(status).isEqualTo(200);
//    }
//
//
//    @DisplayName("인가 테스트")
//    @Test
//    public void authorizationTest() throws Exception {
//        ResultActions resultActions = mockMvc.perform(get("/admins"));
//        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
//        int status = resultActions.andReturn().getResponse().getStatus();
//        System.out.println("테스트: " + responseBody);
//        System.out.println("상태: " + status);
//        Assertions.assertThat(status).isEqualTo(403);
//    }

}