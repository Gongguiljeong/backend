package com.gongguiljeong.domain.admin.service;


import com.gongguiljeong.domain.admin.domain.Admin;
import com.gongguiljeong.domain.admin.domain.AdminJoinRequest;
import com.gongguiljeong.domain.admin.repository.AdminRepository;
import com.gongguiljeong.domain.common.domain.LoginRequest;
import com.gongguiljeong.domain.common.domain.exception.GongguiljeongException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.gongguiljeong.domain.common.domain.exception.ExceptionCode.ADMIN_ALREADY_EXIST;
import static com.gongguiljeong.domain.common.domain.exception.ExceptionCode.ADMIN_NOT_FOUND;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Admin join(AdminJoinRequest joinRequest) {
        boolean existAdmin = adminRepository.existsByUsernameOrEmail(joinRequest.getUsername(), joinRequest.getEmail());
        if (existAdmin) throw new GongguiljeongException(ADMIN_ALREADY_EXIST);
        return adminRepository.save(Admin.from(joinRequest, passwordEncoder));
    }

    public Admin read(Long id) {
        return adminRepository.findById(id).orElseThrow(() -> new GongguiljeongException(ADMIN_NOT_FOUND));
    }

    public Page<Admin> readList(Pageable pageable) {
        return adminRepository.findAll(pageable);
    }

    @Transactional
    public Admin login(LoginRequest loginRequest) {
        Admin admin = adminRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new GongguiljeongException(ADMIN_NOT_FOUND));
        admin.checkPassword(loginRequest.getPassword(), passwordEncoder);
        return admin;
    }
}
