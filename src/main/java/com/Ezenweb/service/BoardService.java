package com.Ezenweb.service;

import com.Ezenweb.domain.dto.BoardDto;
import com.Ezenweb.domain.entity.BoardEntity;
import com.Ezenweb.domain.entity.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service        //service라는걸 알려주기 위한 어노테이션 / 컴포넌트 [Spring MVC]
public class BoardService {
    // --------------------- 1. 전역변수 ------------------------- //
    @Autowired
    private BoardRepository boardRepository;
    //Transactional : 엔티티에 DML 적용할때 사용되는 어노테이션

    // --------------------- 2. 서비스 ------------------------- //
    //1. 게시물 쓰기
    @Transactional
    public boolean setboard(BoardDto boardDto){
        //1. dto -> entity [INSERT] 저장된 entity 반환
        BoardEntity entity = boardRepository.save(boardDto.toEntity());
        //2. 생성된 entity의 게시물번호가 0이 아니면 성공
        if (entity.getBno() != 0) return true;
        else return false;
    }

    //2. 게시물 목록 조회
    @Transactional
    public List<BoardDto> boardlist(){
        List<BoardEntity> entitylist = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for(BoardEntity entity : entitylist){
            boardDtoList.add(entity.toDto());
        }
        return boardDtoList;
    }

    //3. 게시물 개별 조회
    @Transactional
    public BoardDto getboard(@RequestParam("bno") int bno){
        Optional<BoardEntity> optional = boardRepository.findById(bno); //1.입력받은 게시물번호로 엔티티 검색
        if(optional.isPresent())   //2. Optional 안에 있는 내용물 확인
            return optional.get().toDto();     //3. 엔티티 꺼내서 형변환
        else return null;  //3. 없으면 null
    }

    //4. 게시물 삭제
    @Transactional
    public boolean delboard(@RequestParam("bno") int bno){
        Optional<BoardEntity> optional = boardRepository.findById(bno);
        if(optional != null && optional.isPresent()){
            BoardEntity entity = optional.get();
            boardRepository.delete(entity);     //찾은 엔티티 삭제
            return true;
        }
        return false;
    }

    //5. 게시물 수정 [첨부파일]
    @Transactional
    public boolean upboard(@RequestBody BoardDto boardDto){
        Optional<BoardEntity> optional = boardRepository.findById(boardDto.getBno());
        if(optional.isPresent()) {
            BoardEntity entity = optional.get();
            entity.setBtitle(boardDto.getBtitle());
            entity.setBcontent(boardDto.getBcontent());
            entity.setBfile(boardDto.getBfile());
            return true;
        }else return false;
    }
}
