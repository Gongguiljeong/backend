package com.gongguiljeong.domain.image.domain;


import com.gongguiljeong.domain.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "main_images")
public class MainImage extends BaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_image_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Lob
    private String link;

    public MainImage(String name, String link) {
        this.name = name;
        this.link = link;
    }
}
