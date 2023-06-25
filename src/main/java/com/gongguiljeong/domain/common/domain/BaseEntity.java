package com.gongguiljeong.domain.common.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('Y','N')", nullable = false)
    protected Used used = Used.Y;

    @CreatedDate
    protected LocalDateTime createDate;

    @LastModifiedDate
    protected LocalDateTime updateDate;


    public void delete() {
        this.used = Used.N;
    }

    public void restore() {
        this.used = Used.Y;
    }

    public boolean isUsed() {
        return this.used.equals(Used.Y);
    }
}
