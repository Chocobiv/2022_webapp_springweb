package com.Ezenweb.domain.dto;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PageDto {
    private int bcno;       //카테고리 번호
    private int page;       //현재 페이지 번호
    private String key;
    private String keyword;

    @Builder.Default            //빌더 사용시 현재 객체가 기본적으로 할당
    private List<BoardDto> list = new ArrayList<BoardDto>();//검색된 결과[게시물] 리스트
    private int startbtn;       //페이징 버튼 시작번호
    private int endbtn;         //페이징 버튼 끝번호
    private Long totalBoards;   //게시물 전체 개수     getTotalElements 반환타입 : Long

}
