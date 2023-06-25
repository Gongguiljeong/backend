package com.gongguiljeong.domain.user.domain;

import com.gongguiljeong.domain.admin.domain.Role;
import com.gongguiljeong.domain.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String age;


    public User(String name, String email, Gender gender, String age) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }

    public Role getRole() {
        return Role.USER;
    }
}
