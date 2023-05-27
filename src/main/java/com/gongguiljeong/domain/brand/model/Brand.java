package com.gongguiljeong.domain.brand.model;

import com.gongguiljeong.global.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Brand extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long id;


    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String link;

}

