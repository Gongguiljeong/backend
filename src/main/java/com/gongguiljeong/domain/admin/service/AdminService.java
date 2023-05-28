package com.gongguiljeong.domain.admin.service;


import com.gongguiljeong.domain.admin.dto.AdminJoinRequest;
import com.gongguiljeong.domain.admin.dto.AdminLoginRequest;
import com.gongguiljeong.domain.admin.exception.AlreadyExistAdminException;
import com.gongguiljeong.domain.admin.model.Admin;
import com.gongguiljeong.domain.admin.repository.AdminRepository;
import com.gongguiljeong.domain.brand.model.Brand;
import com.gongguiljeong.domain.brand.repository.BrandRepository;
import com.gongguiljeong.domain.brand.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class AdminService {

    private final AdminRepository adminRepository;
    private final BrandService brandService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final BrandRepository brandRepository;


    public void join(AdminJoinRequest adminJoinRequest) {
        Optional<Admin> admin = adminRepository.findByLoginId(adminJoinRequest.getLoginId());
        if (admin.isPresent()) {
            throw new AlreadyExistAdminException();
        }

        admin = adminRepository.findByEmail(adminJoinRequest.getEmail());
        if (admin.isPresent()) {
            throw new AlreadyExistAdminException();
        }

//        if (!brandService.isExist(adminJoinRequest.getBrandName())) {
//            brandService.createBrand(adminJoinRequest.getBrandName(), adminJoinRequest.getBrandLink());
//        }
        Brand brand = brandRepository.findByName(adminJoinRequest.getBrandName()).orElseThrow(RuntimeException::new);


        adminRepository.save(adminJoinRequest.toEntity(brand, passwordEncoder));
    }

}
