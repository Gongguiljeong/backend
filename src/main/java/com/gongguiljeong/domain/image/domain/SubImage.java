package com.gongguiljeong.domain.image.domain;

import com.gongguiljeong.domain.gongguiljeong.domain.Gongguiljeong;
import com.gongguiljeong.domain.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "sub_images")
public class SubImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gongguiljeong_id", nullable = false)
    private Gongguiljeong gongguiljeong;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Lob
    private String link;


    public SubImage(Gongguiljeong gongguiljeong, String name, String link) {
        this.gongguiljeong = gongguiljeong;
        this.name = name;
        this.link = link;
    }
}
