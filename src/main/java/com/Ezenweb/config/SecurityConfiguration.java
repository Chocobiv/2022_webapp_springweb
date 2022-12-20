package com.Ezenweb.config;

import com.Ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MemberService memberService;
    @Override // 인증(로그인) 관련 메소드 재정의
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService( memberService ).passwordEncoder( new BCryptPasswordEncoder() );    //인증은 여기서 하겠다 지정
    }

    @Override   //재정의 [상속받은 클래스로부터 메소드 재구현]
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);  //super : 상속클래스     // 기본값 : 모든 HTTP 보안 설정
        http
            //권한 [role]에 따른 HTTP 제한두기
            .authorizeHttpRequests()//1. 인증 http 요청들 [인증=로그인된] http 조건들
                .antMatchers("/board/write").hasRole("MEMBER")//게시물 쓰기는 회원[MEMBER]만 가능
                .antMatchers("/room/write").hasRole("MEMBER")//게시물 쓰기는 회원[MEMBER]만 가능
                .antMatchers("/board/update/**").hasRole("MEMBER")
                .antMatchers("/admin/**").hasRole("ADMIN")//admin으로 시작하는 경로들은 ADMIN 권한을 가진 사용자만 접근 가능
                .antMatchers("/**").permitAll()//접근 제한 없음 [모든 유저가 사용가능]
            /*.and()
                .exceptionHandling()//오류 페이지에 대한    페이지 매핑
                .accessDeniedPage("/error")//오류 발생 시 해당 URL로 이동*/
            .and()
                .formLogin()                    //로그인 페이지 보안 설정
                .loginPage("/member/login")     //아이디와 비밀번호를 입력받을 URL [로그인 페이지]
                .loginProcessingUrl("/member/getmember")//로그인을 처리할 URL [service로 감 -> loadUserByUsername]
                .defaultSuccessUrl("/")         //로그인 성공했을 때 이동할 URL
                .failureUrl("/member/login")//로그인 실패시 이동할 URL
                .usernameParameter("memail")    //아이디 변수명 [DTO]
                .passwordParameter("mpassword") //비밀번호 변수명 [DTO]
            .and()                  //기능 구분용
                .logout()           //로그아웃 보안 설정
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))//로그아웃 처리 URL 정의
                .logoutSuccessUrl("/") //로그아웃 성공했을 때 이동할 URL
                .invalidateHttpSession(true)//세션 초기화
            .and()                          //기능 구분용
                .csrf()                         //요청 위조 방지
                .ignoringAntMatchers("/member/getmember")   //로그인 post 사용 //해당 URL은 요청 방지 해지하겠다
                .ignoringAntMatchers("/member/setmember")  //회원가입 post 사용
                .ignoringAntMatchers("/board/setbcategory")
                .ignoringAntMatchers("/board/setboard")     //게시물 입력
                .ignoringAntMatchers("/board/boardlist")    //게시물 출력 post사용
                .ignoringAntMatchers("/board/bcategorylist")    //카테고리 출력
                .ignoringAntMatchers("/board/delboard")    //게시물 삭제
                .ignoringAntMatchers("/board/upboard")    //게시물 수정
                .ignoringAntMatchers("/room/write")
            .and()
                .oauth2Login()      //소셜 로그인 보안 설정
                .defaultSuccessUrl("/")//소셜 로그인 성공시 이동하는 URL
                .userInfoEndpoint() //Endpoint(종착점) : 소셜 회원정보가 들어오는 곳
                .userService(memberService);     //해당 서비스에 구현하겠다
    }
}


