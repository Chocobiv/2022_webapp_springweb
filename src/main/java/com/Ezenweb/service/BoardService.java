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
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service        //service라는걸 알려주기 위한 어노테이션 / 컴포넌트 [Spring MVC]
public class BoardService {
    // --------------------- 1. 전역변수 ------------------------- //
    @Autowired
    private MemberRepository memberRepository;  //회원 리포지토리 객체 선언
    @Autowired
    private BoardRepository boardRepository;    //게시판 리포지토리 객체 선언
    @Autowired
    private HttpServletRequest request;         //요청 객체 선언
    @Autowired
    private HttpServletResponse response;       //응답 객체 선언
    @Autowired
    private BcategoryRepository bcategoryRepository;
    @Autowired
    private MemberService memberService;


    //첨부파일 경로
    String path = "C:\\Users\\504\\IdeaProjects\\2022_webapp_springweb\\src\\main\\resources\\static\\bupload\\";

    // --------------------- 2. 서비스 ------------------------- //

    //0. 첨부파일 다운로드
    public void filedownload(String filename) {
        //uuid 제거       //언더바(_)가 여러개일 수 있으므로 아래와 같은 처리를 함
        String realfilename = "";
        String[] split = filename.split("_");   //_기준으로 자르기
        for (int i = 1; i < split.length; i++) {    //첫번째는 uuid이므로 제외하고 나머지
            realfilename += split[i];   //뒷자리 문자열 추가
            if(split.length-1 != i){    //마지막 인덱스가 아니면
                realfilename += "_";    //잘라진 이름 다시 이어 붙이기      문자열[1]_문자열[2]_문자열[3].확장자명
            }
        }

        //1. 경로 찾기
        String filepath = path+filename;
        //2. 헤더 구성 [HTTP에서 지원하는 다운로드형식 메소드 - response]
        try {
            response.setHeader( //응답
                    "Content-Disposition", //다운로드 형식 [브라우저마다 다름]
                    "attachment;filename="+ URLEncoder.encode(realfilename, "UTF-8")); //다운로드에 표시될 파일명

            File file = new File(filepath); //해당 경로의 파일 객체화
            //3. 다운로드 스트림
            BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));//3-1. 입력 스트림 객체 선언
            byte[] bytes = new byte[(int) file.length()];//3-2. 파일의 길이만큼 배열 선언
            fin.read(bytes);    //3-3. 파일의 길이만큼 읽어와서 배열에 저장//스트림 읽기 [대상 : new FileInputStream(file)]
            BufferedOutputStream fout = new BufferedOutputStream(response.getOutputStream());//3-4. 출력 스트림 객체 선언
            fout.write(bytes);  //스트림 내보내기 [대상 : response.getOutputStream()]//3-5. 응답하기 [배열 내보내기]
            fout.flush(); fout.close(); fin.close();          //3-6. 버퍼 초기화 혹은 스트림 닫기
        }catch(Exception e){ System.out.println(e); }
    }

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

            if(boardDto.getBfile() != null) {   // ** 첨부파일 있을 때
                // ----- 업로드된 파일의 이름 [문제점 : 파일명 중복] ----- //
                //1. pk + 파일명 [게시판테이블, 첨부파일테이블]
                //2. uuid[범용고유식별자] 클래스 + 파일명
                String uuid = UUID.randomUUID().toString();   //1. 난수생성
                //실제 유저가 업로드한 파일의 확장자가 깨질 수 있으므로 uuid를 앞에다 쓰기
                String filename = uuid + "_" + boardDto.getBfile().getOriginalFilename();  //2. 난수+파일명
                //3. 업로드 날짜/시간 + 파일명
                //4. 중복된 파일명 중 최근파일명 뒤에 파일명 + (중복수+1)


                // ----- 첨부파일명 DB에 등록 ----- //
                boardEntity.setBfile(filename);     //해당 파일명을 엔티티에 저장 //3. 난수+파일명을 엔티티에 저장

                // ----- 첨부파일 업로드 ----- //    저장할 경로 [전역변수]
                try {
                    File uploadfile = new File(path + filename);    //4. 경로+파일명 [객체화]
                    boardDto.getBfile().transferTo(uploadfile);             //5. 해당 객체 경로로 업로드
                } catch (Exception e) {
                    System.out.printf(" 첨부파일 업로드 실패");
                }
            }

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
            entity.setBview(entity.getBview()+1);       //조회수 증가
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
