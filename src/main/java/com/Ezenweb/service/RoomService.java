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
                RoomImgEntity roomImgEntity =
                roomImgRepository.save(RoomImgEntity.builder().rimg(img.getOriginalFilename()).build());//필드가 적을 때는 굳이 DTO 필요 없음
                //2-5. 방에 사진엔티티 대입  //2-6. 사진엔티티에 방 대입 [양방향 관계]
                roomEntity.getRoomImgEntityList().add(roomImgEntity);
                roomImgEntity.setRoomEntity(roomEntity);
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
