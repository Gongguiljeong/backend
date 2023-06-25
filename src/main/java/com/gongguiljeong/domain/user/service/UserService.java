package com.gongguiljeong.domain.user.service;

import com.gongguiljeong.domain.user.domain.KakaoProfile;
import com.gongguiljeong.domain.user.domain.Gender;
import com.gongguiljeong.domain.user.domain.User;
import com.gongguiljeong.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
