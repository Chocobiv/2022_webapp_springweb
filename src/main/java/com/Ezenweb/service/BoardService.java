package com.Ezenweb.service;

import com.Ezenweb.domain.dao.BoardDao;
import com.Ezenweb.domain.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service        //service라는걸 알려주기 위한 어노테이션 / 컴포넌트 [Spring MVC]
public class BoardService {

    //@Autowired      //생성자 생략 가능하도록 해주는 어노테이션
    //BoardDao boardDao;

    //1. 게시물 등록 서비스
    public boolean setboard(BoardDto boardDto){
        return new BoardDao().setboard(boardDto);
    }
    //2. 게시물 목록 서비스
    public ArrayList<BoardDto> getboards(){
        return new BoardDao().getboards();
    }

    //3. 게시물 개별 조회 서비스
    public BoardDto getboard(int bno){
        return new BoardDao().getboard(bno);
    }


}
