package com.gongguiljeong.domain.admin.domain;

import com.gongguiljeong.domain.brand.domain.Brand;
import com.gongguiljeong.domain.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "admins")
public class Admin extends BaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    public Admin(Brand brand, String name, String username, String password, String email) {
        this.brand = brand;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }


    public Role getRole() {
        return Role.ADMIN;
    }
}

