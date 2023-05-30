package com.gongguiljeong.domain.user.service;

import com.gongguiljeong.domain.user.dto.KakaoProfile;
import com.gongguiljeong.domain.user.model.Gender;
import com.gongguiljeong.domain.user.model.User;
import com.gongguiljeong.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    @Transactional
    public User login(KakaoProfile kakaoProfile) {
        return userRepository.findByEmail(kakaoProfile.getEmail())
                .orElseGet(() -> userRepository.save(new User("카카오닉네임", kakaoProfile.getEmail(), Gender.N, "0")));
    }

}
