package com.gongguiljeong.domain.admin.service;


import com.gongguiljeong.domain.admin.dto.AdminJoinRequest;
import com.gongguiljeong.domain.admin.exception.AlreadyExistAdminException;
import com.gongguiljeong.domain.admin.repository.AdminRepository;
import com.gongguiljeong.domain.brand.exception.BrandNotFoundException;
import com.gongguiljeong.domain.brand.model.Brand;
import com.gongguiljeong.domain.brand.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class AdminService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final BrandRepository brandRepository;


    public void join(AdminJoinRequest adminJoinRequest) {
        boolean isExistAdmin = adminRepository.existsByLoginId(adminJoinRequest.getLoginId());
        if (isExistAdmin) {
            throw new AlreadyExistAdminException();
        }

        isExistAdmin = adminRepository.existsByEmail(adminJoinRequest.getEmail());
        if (isExistAdmin) {
            throw new AlreadyExistAdminException();
        }

        Brand brand = brandRepository.findById(adminJoinRequest.getBrandId()).orElseThrow(BrandNotFoundException::new);
        adminRepository.save(adminJoinRequest.toEntity(brand, passwordEncoder));
    }
}
