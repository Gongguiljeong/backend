package com.gongguiljeong.domain.admin.service;


import com.gongguiljeong.domain.admin.domain.Admin;
import com.gongguiljeong.domain.admin.domain.AdminJoinRequest;
import com.gongguiljeong.domain.admin.domain.AdminResponse;
import com.gongguiljeong.domain.admin.domain.LoginRequest;
import com.gongguiljeong.domain.admin.repository.AdminRepository;
import com.gongguiljeong.domain.brand.domain.Brand;
import com.gongguiljeong.domain.brand.repository.BrandRepository;
import com.gongguiljeong.domain.common.domain.exception.ExceptionCode;
import com.gongguiljeong.domain.common.domain.exception.GongguiljeongException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AdminService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final BrandRepository brandRepository;


    @Transactional
    public void join(AdminJoinRequest adminJoinRequest) {
        boolean existAdmin = adminRepository.existsByUsername(adminJoinRequest.getUsername());
        if (existAdmin) {
            throw new GongguiljeongException(ExceptionCode.BRAND_ALREADY_EXIST);
        }

        existAdmin = adminRepository.existsByEmail(adminJoinRequest.getEmail());
        if (existAdmin) {
            throw new GongguiljeongException(ExceptionCode.BRAND_ALREADY_EXIST);
        }

        Brand brand = brandRepository.findById(adminJoinRequest.getBrandId()).orElseThrow(() -> new GongguiljeongException(ExceptionCode.BRAND_NOT_FOUND));
        adminRepository.save(adminJoinRequest.toEntity(brand, passwordEncoder));
    }

    public AdminResponse readAdmin(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new GongguiljeongException(ExceptionCode.ADMIN_NOT_FOUND));
        return new AdminResponse(admin);
    }

    public Page<AdminResponse> readAdminList(Pageable pageable) {
        return adminRepository.findAll(pageable).map(AdminResponse::new);

    }


    @Transactional
    public Admin login(LoginRequest loginRequest) {
        Admin admin = adminRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new GongguiljeongException(ExceptionCode.ADMIN_NOT_FOUND));
        if (!passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword())) {
            throw new GongguiljeongException(ExceptionCode.ADMIN_NOT_FOUND);
        }
        return admin;
    }
}
