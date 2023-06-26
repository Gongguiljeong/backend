package com.gongguiljeong.domain.admin.repository;

import com.gongguiljeong.domain.admin.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    boolean existsByUsernameOrEmail(String username, String email);


    Optional<Admin> findByUsername(String username);
}
