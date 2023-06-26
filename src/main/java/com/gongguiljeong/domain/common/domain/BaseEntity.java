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
public abstract class BaseEntity {

    @Enumerated(EnumType.STRING)
    protected Status status = Status.Y;

    @CreatedDate
    protected LocalDateTime createDate;

    @LastModifiedDate
    protected LocalDateTime updateDate;


    public void delete() {
        this.status = Status.N;
    }

    public void restore() {
        this.status = Status.Y;
    }

    public boolean isStatus() {
        return this.status.equals(Status.Y);
    }
}
