package com.Ezenweb.controller;

import com.Ezenweb.domain.dto.BoardDto;
import com.Ezenweb.service.BoardService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController             //해당 클래스가 컨트롤 목적 사용
@RequestMapping("/board")   //  해당 클래스 안에 있는 Mapping들의 공통 URL
public class BoardController {

    // ------------------------ HTML LOAD URL ---------------------------
    //1. 게시물 목록 페이지 열기 [HTML] 열기
    @GetMapping("/boardlist")       //URL 정의하기
    public Resource boardList() {
        return new ClassPathResource("templates/board/list.html");
    }

    //2. 게시물 쓰기 페이지 열기
    @GetMapping("/write")           //URL 정의하기
    public Resource write() {
        return new ClassPathResource("templates/board/write.html");     //Resource 반환타입[contentType] : text/html
    }

    // ---------------------------- 기능 처리 -----------------------------
    //1. 게시물 쓰기 처리 [첨부파일]
    @PostMapping("/setboard")
    public boolean setboard( @RequestBody BoardDto boardDto){
        //1. DTO 내용 확인
        System.out.println(boardDto.toString());
        //2. 유효성 검사 -> 서비스[비즈니스 로직]로 이동
        boolean result = new BoardService().setboard(boardDto);
        //3. 반환
        return result;    //boolean 반환타입[contentType] : application/json
    }
    //2. 게시물 목록 보기 처리 [페이징, 검색]
    @GetMapping("/getboards")
    public ArrayList<BoardDto> getboards(){
        //1. -> 서비스[비즈니스 로직]로 이동
        ArrayList<BoardDto> list = new BoardService().getboards();
        //2. 반환
        return list;
    }
    //3. 게시물 개별 조회 처리
    @GetMapping("/getboard")
    public BoardDto getboard(@PathVariable int bno){
        BoardDto boardDto = new BoardService().getboard(bno);
        return boardDto;
    }
    //4. 게시물 수정 처리
    @PutMapping("/updateboard")
    public boolean updateboard(){

        return true;
    }
    //5. 게시물 삭제 처리
    @DeleteMapping("/deleteboard")
    public boolean  deleteboard(){
        return true;
    }

}
