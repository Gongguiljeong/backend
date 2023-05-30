package com.gongguiljeong.domain.interest_gongguiljeong.model;

import com.gongguiljeong.domain.gongguiljeong.model.Gongguiljeong;
import com.gongguiljeong.domain.user.model.User;
import com.gongguiljeong.global.base_model.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class InterestGongguiljeong extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interest_gongguiljeong_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false )
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gongguiljeong_id", nullable = false)
    private Gongguiljeong gongguiljeong;

}
