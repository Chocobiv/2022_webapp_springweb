package com.Ezenweb.controller;

import com.Ezenweb.domain.dto.MemberDto;
import com.Ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")    //요청 포트를 변경해주는 어노테이션
@RestController     //Restful API 사용하는 controller 명시 [controller + @ResponseBody]
@RequestMapping("/member")      //공통 URL 매핑 주소
public class MemberController {

    // ----------------------- 전역 객체 -------------------------------
    @Autowired      //제어역전[DI] 메모리 관리 스프링 컨테이너에 위임
    private MemberService memberService;    //서비스 객체 생성

    // ----------------------- HTML 반환 매핑 -------------------------------
    @GetMapping("/signup")//프로젝트 내 html을 반환(매핑)
    public Resource getsignup() { return new ClassPathResource("templates/member/signup.html"); }

    @GetMapping("/login")
    public Resource getlogin() {//프로젝트 내 html을 반환(매핑)
        return new ClassPathResource("templates/member/login.html"); }

    @GetMapping("/findpassword")
    public Resource findpassword() { return new ClassPathResource("templates/member/findpassword.html"); }

    @GetMapping("/delete")
    public Resource getdelete() { return new ClassPathResource("templates/member/delete.html"); }

    @GetMapping("/update")
    public Resource getupdate() { return new ClassPathResource("templates/member/update.html"); }

    @GetMapping("/logout")
    public Resource getlogout() { return new ClassPathResource("templates/member/logout.html"); }
    // ----------------------- 서비스/기능 매핑 -------------------------------
    @PostMapping("/setmember")      //1. 회원가입 기능
    public int setmember(@RequestBody MemberDto memberDto){
        //1. 테스트 dto 생성
        /*MemberDto memberDto = MemberDto.builder()
                                        .memail("qwe@naver.com")
                                        .mpassword("123123")
                                        .build();
        */
        //2. 서비스 메소드 호출
        //MemberService에서 반환하는 mno값이 반환됨
        int result = memberService.setmember(memberDto);  //서비스 [비즈니스 로직] 호출
        //3. 응답
        return result;
    }

/*    @PostMapping("/getmember")      //2. 로그인 기능 [시큐리티 사용시 필요없음]
    public int getmember(@RequestBody MemberDto memberDto){
        int result = memberService.getmember(memberDto);
        return result;
    }*/

    @GetMapping("/getpassword")     //3. 비밀번호찾기
    public String getpassword(@RequestParam("memail") String memail){
        String result = memberService.getpassword(memail);
        return result;
    }

    @DeleteMapping("/setdelete")    //4. 회원탈퇴
    public int setdelete(@RequestParam("mpassword") String mpassword){
        //1. 서비스 처리
        int result = memberService.setdelete(mpassword);
        //2. 서비스 결과 반환
        return result;
    }

    @PutMapping("/setupdate")       //5. 회원정보수정
    public int setupdate(@RequestParam("mpassword") String mpassword){
        int result = memberService.setupdate(mpassword);
        return result;
    }

    @GetMapping("/getloginMno")     //6. 로그인 정보 확인
    public int getloginMno(){
        int result = memberService.getloginMno();
        return result;
    }

    @PutMapping("/setlogout")       //7. 로그아웃
    public void setupdate(){
        memberService.setlogout();
    }

    @GetMapping("/list")            //8. 회원목록
    public List<MemberDto> list() {
        return memberService.list();
    }

    @GetMapping("/getauth")
    public String getauth(@RequestParam("toemail") String toemail) {
        return memberService.getauth(toemail);
    }
}
