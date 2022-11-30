package com.Ezenweb.domain.entity.member;

import com.Ezenweb.domain.dto.MemberDto;
import com.Ezenweb.domain.entity.BaseEntity;
import com.Ezenweb.domain.entity.board.BoardEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity         //해당 연결된 DB의 테이블과 매핑[연결]
@Table(name = "member")     //DB에서 사용될 테이블 이용
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class MemberEntity extends BaseEntity {
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

    @OneToMany(mappedBy = "memberEntity")  //[1:n] PK에 사용하는 어노테이션   mappedBy="fk필드명"
    @Builder.Default        //빌더 사용시 해당 필드의 초기값 설정 [값을 안넣으면 그냥 깡통 들어감]
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    @Column
    private String mrol;            //회원 등급 필드


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
