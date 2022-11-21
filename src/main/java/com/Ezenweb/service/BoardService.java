package com.Ezenweb.service;

import com.Ezenweb.domain.dto.BoardDto;
import com.Ezenweb.domain.entity.board.BoardEntity;
import com.Ezenweb.domain.entity.board.BoardRepository;
import com.Ezenweb.domain.entity.member.MemberEntity;
import com.Ezenweb.domain.entity.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service        //service라는걸 알려주기 위한 어노테이션 / 컴포넌트 [Spring MVC]
public class BoardService {
    // --------------------- 1. 전역변수 ------------------------- //
    @Autowired
    private MemberRepository memberRepository;  //회원 리포지토리 객체 선언
    @Autowired
    private BoardRepository boardRepository;    //게시판 리포지토리 객체 선언
    @Autowired
    private HttpServletRequest request;

    // --------------------- 2. 서비스 ------------------------- //
    //1. 게시물 쓰기
    @Transactional
    public boolean setboard(BoardDto boardDto){
        //1. 로그인 정보 확인 [세션 = loginMno]
        Object object = request.getSession().getAttribute("loginMno");
        if(object == null){ return false; }     //로그인이 안됐으면 그냥 종료

        //2. 로그인된 회원번호
        int mno = (Integer)object;

        //3. 회원번호 -> 회원정보 호출
        Optional<MemberEntity> optional = memberRepository.findById(mno);
        if(!optional.isPresent()){ return false; }
        //4.
        MemberEntity memberEntity = optional.get();


        BoardEntity boardEntity = boardRepository.save(boardDto.toEntity()); //1. dto -> entity [INSERT] 저장된 entity 반환
        if (boardEntity.getBno() != 0) {//2. 생성된 entity의 게시물번호가 0이 아니면 성공
            // *** 5. fk 대입 [Board에 Member 넣고]
            boardEntity.setMemberEntity(memberEntity);       //set했기때문에 @Transactional 꼭 필요
            // *** 양방향 [pk필드에 fk 연결] [Member에 Board 넣기]
            memberEntity.getBoardEntityList().add(boardEntity);
            return true;
        }else return false;
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
        if(optional.isPresent()) {   //2. Optional 안에 있는 내용물 확인
            BoardEntity entity = optional.get();
            entity.setBview(entity.getBview()+1);
            return entity.toDto();     //3. 엔티티 꺼내서 형변환
        }else return null;  //3. 없으면 null
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
