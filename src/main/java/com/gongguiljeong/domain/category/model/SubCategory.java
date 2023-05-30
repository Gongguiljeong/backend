package com.gongguiljeong.domain.category.model;

import com.gongguiljeong.global.base_model.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SubCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_category_id", nullable = false)
    private MainCategory mainCategory;

    @Column(nullable = false, unique = true)
    private String name;

    public SubCategory(MainCategory mainCategory, String name) {
        this.mainCategory = mainCategory;
        this.name = name;
    }
}
