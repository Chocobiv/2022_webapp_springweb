package com.Ezenweb.service;

import com.Ezenweb.domain.dto.MemberDto;
import com.Ezenweb.domain.entity.MemberEntity;
import com.Ezenweb.domain.entity.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service            //해당 클래스가 Service 컴포넌트임을 명시     //1. 비즈니스 로직 [알고리즘 - 기능]
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Transactional  //트랜잭션
    public int setmember(MemberDto memberDto){
        //1. 비즈니스 로직 [알고리즘 - 기능]
        //2. DAO 처리
        MemberEntity entity = memberRepository.save(memberDto.toEntity());  //dto -> entity
        //memberRepository.save(엔티티) : 해당 엔티티를 insert
        entity.setMemail("asdfg@gmail.com");    //엔티티 <-> 레코드 매핑
        return entity.getMno();
    }
}
