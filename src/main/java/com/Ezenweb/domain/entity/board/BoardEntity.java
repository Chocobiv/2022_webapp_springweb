package com.Ezenweb.domain.entity.board;

import com.Ezenweb.domain.dto.BoardDto;
import com.Ezenweb.domain.entity.BaseEntity;
import com.Ezenweb.domain.entity.bcategory.BcategoryEntity;
import com.Ezenweb.domain.entity.member.MemberEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity //엔티티 정의
@Table(name = "board")  //테이블명 정의
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class BoardEntity extends BaseEntity {

    //1. 필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;            //게시물 번호
    @Column(nullable = false)   //not null
    private String btitle;      //게시물 제목
    @Column(nullable = false, columnDefinition = "TEXT")   //not null, columnDefinition = "DB자료형"
    private String bcontent;    //게시물 내용

    @Column(nullable = false)   //not null
    @ColumnDefault("0")         //JPA insert 할 경우 default
    private int bview;          //조회수
    //@Column(nullable = false)   //not null
    private String bfile;       //첨부파일
    //작성일, 수정일 -> 상속 (여러 엔티티에서 사용되는 필드라서)


    //연관관계1 [회원번호[pk] <--양방향--> 게시물번호[fk]]
    @ManyToOne      //[n:1] fk에 해당 어노테이션
    @JoinColumn(name="mno") //테이블에서 사용할 fk의 필드명 정의
    @ToString.Exclude       //해당 필드는 ToString()에서 사용하지 않는다.
    private MemberEntity memberEntity; //pk에 엔티티 객체

    //연관관계2 [카테고리번호[pk] <--양방향--> 게시물번호[fk]]
    @ManyToOne  //[1:n] FK에 해당 어노테이션
    @JoinColumn(name="bcno")
    @ToString.Exclude
    private BcategoryEntity bcategoryEntity;

    //1. 형변환
    public BoardDto toDto() {
        return BoardDto
                .builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .bfile(this.bfile)
                .memail(this.memberEntity.getMemail().split("@")[0])
                .build();
    }
}
