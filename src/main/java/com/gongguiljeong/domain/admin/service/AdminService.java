package com.gongguiljeong.domain.admin.service;


import com.gongguiljeong.domain.admin.dto.AdminJoinRequest;
import com.gongguiljeong.domain.admin.dto.AdminResponse;
import com.gongguiljeong.domain.admin.exception.AlreadyExistAdminException;
import com.gongguiljeong.domain.admin.model.Admin;
import com.gongguiljeong.domain.admin.repository.AdminRepository;
import com.gongguiljeong.domain.brand.exception.BrandNotFoundException;
import com.gongguiljeong.domain.brand.model.Brand;
import com.gongguiljeong.domain.brand.repository.BrandRepository;
import com.gongguiljeong.domain.gongguiljeong.service.AdminNotFoundException;
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

    public AdminResponse readAdmin(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(AdminNotFoundException::new);
        return new AdminResponse(admin);
    }

    public Page<AdminResponse> readAdminList(Pageable pageable) {
        return adminRepository.findAll(pageable).map(AdminResponse::new);

    }
}
