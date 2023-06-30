package com.gongguiljeong.domain.interest_gongguiljeong.service;

import com.gongguiljeong.domain.common.domain.exception.GongguiljeongException;
import com.gongguiljeong.domain.gongguiljeong.domain.Gongguiljeong;
import com.gongguiljeong.domain.gongguiljeong.repository.GongguiljeongRepository;
import com.gongguiljeong.domain.interest_gongguiljeong.domain.InterestGongguiljeong;
import com.gongguiljeong.domain.interest_gongguiljeong.repository.InterestGongguiljeongRepository;
import com.gongguiljeong.domain.user.domain.User;
import com.gongguiljeong.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.gongguiljeong.domain.common.domain.exception.ExceptionCode.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InterestGongguiljeongService {
    private final InterestGongguiljeongRepository interestGongguiljeongRepository;
    private final GongguiljeongRepository gongguiljeongRepository;
    private final UserRepository userRepository;


    @Transactional
    public InterestGongguiljeong register(Long gongguiljeongId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new GongguiljeongException(USER_NOT_FOUND));
        if (interestGongguiljeongRepository.findByGongguiljeongId(gongguiljeongId).isPresent()) {
            throw new GongguiljeongException(INTEREST_GONGGUILJEONG_ALREADY_EXIST);
        }
        Gongguiljeong gongguiljeong = gongguiljeongRepository.findById(gongguiljeongId).orElseThrow(() -> new GongguiljeongException(GONGGUILJEONG_NOT_FOUND));
        InterestGongguiljeong interestGongguiljeong = InterestGongguiljeong.builder()
                .user(user)
                .gongguiljeong(gongguiljeong)
                .build();
        return interestGongguiljeongRepository.save(interestGongguiljeong);
    }


    @Transactional
    public InterestGongguiljeong delete(Long userId, Long interestId) {
        InterestGongguiljeong interestGongguiljeong = interestGongguiljeongRepository.findByIdAndUserId(interestId, userId).orElseThrow(() -> new GongguiljeongException(INTEREST_GONGGUILJEONG_NOT_FOUND));
        interestGongguiljeong.delete();
        return interestGongguiljeong;
    }

    public Page<InterestGongguiljeong> findByUserId(Long userId, Pageable pageable) {
        return interestGongguiljeongRepository.findByUserId(userId, pageable);

    }

    public Page<InterestGongguiljeong> findByCategoryId(Pageable pageable, Long mainCategoryId, Long subCategoryId) {
        return interestGongguiljeongRepository.findByMainCategoryIdAndSubCategoryId(pageable, mainCategoryId, subCategoryId);
    }
}
