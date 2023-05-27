package com.gongguiljeong.domain.user.repository;


import com.gongguiljeong.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
