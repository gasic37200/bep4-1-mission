package com.back.global.jap.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

// JPA가 부모 클래스의 필드를 DB 매핑에 포함하도록 만드는 설정
// 일반 부모 클래스에 선언된 필드는 JPA의 영속 매핑 대상이 아님
@MappedSuperclass
@Getter
// 모든 엔티티들의 조상
public abstract class BaseEntity {
    public abstract int getId();

    public abstract LocalDateTime getCreateDate();

    public abstract LocalDateTime getModifyDate();

    // 오류 코드 출력
    public String getModelTypeCode() {
        return this.getClass().getSimpleName();
    }
}
