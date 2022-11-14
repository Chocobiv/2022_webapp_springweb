package com.Ezenweb.controller;

import com.Ezenweb.domain.dto.MemberDto;
import com.Ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController     //Restful API 사용하는 controller 명시
public class MemberController {

    @Autowired      //제어역전[DI] 메모리 관리 스프링 컨테이너에 위임
    private MemberService memberService;

    @GetMapping("/setmember")
    public int setmember(){
        //1. 테스트 dto 생성
        MemberDto memberDto = MemberDto.builder()
                                        .memail("qwe@naver.com")
                                        .mpassword("123123")
                                        .build();

        //2. 서비스 메소드 호출
        int result = memberService.setmember(memberDto);  //서비스 [비즈니스 로직] 호출
        //3. 응답
        return result;

    }
}
