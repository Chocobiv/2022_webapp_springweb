package com.Ezenweb.service;

import com.Ezenweb.domain.dto.RoomDto;
import com.Ezenweb.domain.entity.member.MemberEntity;
import com.Ezenweb.domain.entity.member.MemberRepository;
import com.Ezenweb.domain.entity.room.RoomEntity;
import com.Ezenweb.domain.entity.room.RoomImgEntity;
import com.Ezenweb.domain.entity.room.RoomImgRepository;
import com.Ezenweb.domain.entity.room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomImgRepository roomImgRepository;

    //현재 스프링의 배포된 내장서버 폴더
    //String path = "C:\\Users\\504\\IdeaProjects\\2022_webapp_springweb\\build\\resources\\main\\static\\bupload\\"; //나중에 배포할 때는 서버에 업로드해야하므로 경로 설정 이렇게[단, 유료 버전이면 자동 빌드되서 상관x]
    //현재 스프링의 배포된 내장 서버 내 리액트 리소스 [static->media] 폴더 경로
    //서버[내장서버]가 재시작되면 빌드폴더에 업로드 파일 사라짐
    String path = "C:\\Users\\504\\IdeaProjects\\2022_webapp_springweb\\build\\resources\\main\\static\\static\\media\\";

    @Transactional
    public boolean write(RoomDto roomDto){
        //1. 로그인한 유저 정보 확인
        String loginMemail = memberService.getloginMno();
        if(loginMemail == null){ return false; }
        MemberEntity memberEntity = memberRepository.findByMemail(loginMemail).get();

        //2. 방 등록[PK] -> 우선 처리
        //2-1. 방 저장 [save 반환타입 : 저장된 매핑 레코드]
        RoomEntity roomEntity= roomRepository.save(roomDto.toEntity());
        if(roomEntity.getRno() < 1){ return false; }    //실패 시
        //2-2. 방에 회원엔티티 대입  //2-3. 회원엔티티에 방 대입 [양방향 관계]
        roomEntity.setMemberEntity(memberEntity);
        memberEntity.getRoomEntityList().add(roomEntity);
        //2-4. 사진등록 저장
        roomDto.getRimg().forEach((img)->{  //첨부파일 여러개일 경우 혹은 존재할 경우 -> 반복문
            if(!img.getOriginalFilename().equals("")){//실제 첨부파일의 파일명이 존재할 경우
                RoomImgEntity roomImgEntity = roomImgRepository.save(RoomImgEntity.builder().build());//필드가 적을 때는 굳이 DTO 필요 없음
                //2-5. 방에 사진엔티티 대입  //2-6. 사진엔티티에 방 대입 [양방향 관계]
                roomEntity.getRoomImgEntityList().add(roomImgEntity);
                roomImgEntity.setRoomEntity(roomEntity);

                //첨부파일 사진 업로드
                try {
                    //첨부파일에 식별자 추가 [pk+파일명(뒤에다가 안 붙이는 이유는 확장자명이 깨질 수 있기 때문!)]
                    String filename = roomImgEntity.getRimgno()+img.getOriginalFilename();//img.getOriginalFilename() : 첨부파일된 실제 파일명
                    roomImgEntity.setRimg(filename);//DB에 식별자가 추가된 파일명으로 변경
                    File file = new File(path+filename);//경로+첨부파일명 => file 객체화 [transferTo함수의 인수로 file 객체 사용]
                    img.transferTo(file);//transferTo : MultipartFile 인터페이스의 업로드[파일 이동] 함수      기존파일.transferTo(이동할 경로) //예외처리 필수
                }catch (Exception e) { System.out.println("[업로드 실패] " + e); }
            }
        });
        //3. 사진 등록[FK] -> 나중 처리
        return true;
    }

    //2. 방 출력
    public List<RoomDto> getroomlist() {
        //1. 모든 방 엔티티 꺼내기
        List<RoomEntity> roomEntityList = roomRepository.findAll(); // 매핑된 엔티티들
        List<RoomDto> roomDtoList = new ArrayList<>();// 출력용 DTO

        //2. 형변환
        roomEntityList.forEach((e)->{
            roomDtoList.add(e.toDto());
        });
        return roomDtoList;
    }
}
