package com.Ezenweb.service;

import com.Ezenweb.domain.dto.BcategoryDto;
import com.Ezenweb.domain.dto.BoardDto;
import com.Ezenweb.domain.entity.bcategory.BcategoryEntity;
import com.Ezenweb.domain.entity.bcategory.BcategoryRepository;
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
    @Autowired
    private BcategoryRepository bcategoryRepository;
    @Autowired
    private MemberService memberService;

    // --------------------- 2. 서비스 ------------------------- //
    //1. 게시물 쓰기
    @Transactional
    public boolean setboard(BoardDto boardDto){
        // ---------------------- 로그인 회원 찾기 메소드 실행 -> 회원 엔티티 검색 ---------------------- //
        MemberEntity memberEntity = memberService.getEntity();  //로그인된 회원 엔티티
        if(memberEntity == null) { return false; }      //로그인이 안되어 있으면 함수 종료

        // ---------------------- 선택한 카테고리 번호 -> 카테고리 엔티티 검색 ---------------------- //
        Optional<BcategoryEntity> optional = bcategoryRepository.findById(boardDto.getBcno());
        if(!optional.isPresent()) { return false; }
        BcategoryEntity bcategoryEntity = optional.get();

        BoardEntity boardEntity = boardRepository.save(boardDto.toEntity()); //1. dto -> entity [INSERT] 저장된 entity 반환
        if (boardEntity.getBno() != 0) {//2. 생성된 entity의 게시물번호가 0이 아니면 성공
            //1. 회원 <--> 게시물 연관관계 대입 [양방향]
            boardEntity.setMemberEntity(memberEntity);     // *** 5. fk 대입 [Board에 Member 넣고]  //set했기때문에 @Transactional 꼭 필요
            memberEntity.getBoardEntityList().add(boardEntity);// *** 양방향 [pk필드에 fk 연결] [Member에 Board 넣기]
            //2. 카테고리 <--> 게시물 연관관계 대입 [양방향]
            boardEntity.setBcategoryEntity(bcategoryEntity);    //내가 클릭한 카테고리를 넣어주기
            bcategoryEntity.getBoardEntityList().add(boardEntity);
            return true;
        }else return false;
    }

    //2. 게시물 목록 조회
    @Transactional
    public List<BoardDto> boardlist(int bcno){
        List<BoardEntity> entitylist = null;
        if(bcno == 0) { entitylist = boardRepository.findAll(); }//카테고리번호가 0이면 전체보기
        else{   //카테고리번호가 0이 아니면 선택된 카테고리별 보기
            BcategoryEntity bcEntity  = bcategoryRepository.findById(bcno).get();
            entitylist = bcEntity.getBoardEntityList();  //해당 엔티티의 게시물 목록
        }
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

    //6. 카테고리 등록
    public boolean setbcategory(BcategoryDto bcategoryDto){
        BcategoryEntity bc = bcategoryRepository.save(bcategoryDto.toEntity());
        if(bc.getBcno() != 0) return true;
        else return false;
    }

    //7. 모든 카테고리 출력
    public List<BcategoryDto> bcategorylist(){
        List<BcategoryEntity> entityList = bcategoryRepository.findAll();
        List<BcategoryDto> dtoList = new ArrayList<>();
        entityList.forEach( e -> dtoList.add(e.toDto()) );   //화살표함수[람다식표현] java : 인수 -> {실행코드}     js : (인수) => {실행코드}

        return dtoList;
    }
}
