package com.gongguiljeong.domain.influencer.domain;


import com.gongguiljeong.domain.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "influencers")
public class Influencer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "influencer_id")
    private Long id;
    private String name;
    private String link;

    public Influencer(String name, String link) {
        this.name = name;
        this.link = link;
    }
}

