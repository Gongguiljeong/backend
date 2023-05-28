package com.gongguiljeong.domain.admin.repository;

import com.gongguiljeong.domain.admin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByLoginId(String loginId);

    Optional<Admin> findByEmail(String email);
}
