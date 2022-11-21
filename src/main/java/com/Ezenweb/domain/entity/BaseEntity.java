package com.Ezenweb.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter @Setter @ToString         //롬복
@MappedSuperclass       //상속받을 경우 자식 클래스에게 매핑 정보 전달
@EntityListeners(AuditingEntityListener.class)//해당 클래스 엔티티 감시기능
public class BaseEntity {

    @CreatedDate        //데이터의 생성 날짜를 자동으로 주입
    @Column(updatable = false)      //수정 불가
    private LocalDateTime cdate;

    @LastModifiedDate   //데이터 수정 날짜를 자동 주입
    private LocalDateTime udate;

}
