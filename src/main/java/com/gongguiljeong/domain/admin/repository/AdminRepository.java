package com.gongguiljeong.domain.admin.repository;

import com.gongguiljeong.domain.admin.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    boolean existsByLoginId(String loginId);

    boolean existsByEmail(String email);

    Optional<Admin> findByLoginId(String loginId);
}
