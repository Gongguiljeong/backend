package com.gongguiljeong.domain.category.domain;

import com.gongguiljeong.domain.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "main_categories")
@Where(clause = "status = 'Y'")
public class MainCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_category_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public MainCategory(String name) {
        this.name = name;
    }
}
