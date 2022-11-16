package com.Ezenweb.domain.entity;

import com.Ezenweb.domain.dto.MemberDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity         //해당 연결된 DB의 테이블과 매핑[연결]
@Table(name = "member")     //DB에서 사용될 테이블 이용
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MemberEntity {
    //1. 필드
    @Id         //엔티티당 무조건 1개이상 [PK]
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 번호 부여
    private int mno;                //회원번호 필드
    @Column( nullable = false )     //not null
    private String memail;          //회원이메일 = 회원아이디 필드
    @Column( nullable = false )     //not null
    private String mpassword;       //회원비밀번호 필드

    @Column( nullable = false )     //not null
    private String mphone;          //회원전화번호 필드

    //2. 생성자 [lombok]
    //3. method [lombok]

    //엔티티 -> DTO 형변환
    public MemberDto toDto(){
        return MemberDto
                .builder()  //빌더 메소드 시작
                .mno(this.mno)
                .memail(this.memail)
                .mpassword(this.mpassword)
                .mphone(this.mphone)
                .build();   //빌드 메소드 끝
    }
}
