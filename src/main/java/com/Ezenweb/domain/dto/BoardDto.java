package com.Ezenweb.domain.dto;

import com.Ezenweb.domain.entity.board.BoardEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

//lombok: 생성자, Get/Set, ToString, 빌더패턴
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString @Builder
public class BoardDto {
    //1. 필드
    private int bno;            //게시물 번호
    private String btitle;      //게시물 제목
    private String bcontent;    //게시물 내용
    //작성일, 수정일 -> 상속 (여러 엔티티에서 사용되는 필드라서)
    private int bview;          //조회수
    private MultipartFile bfile;//첨부파일 [업로드용]
    private String bfilename;   //첨부파일 [출력용]

    private int mno;            //작성자 회원번호
    private int bcno;           //카테고리 [카테고리-fk]
    private String memail;      //회원아이디

    private int startbtn;
    private int endbtn;

    //1. 형변환
    public BoardEntity toEntity() {
        return BoardEntity.builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .build();
    }
}
