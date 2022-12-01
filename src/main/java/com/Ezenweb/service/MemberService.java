package com.Ezenweb.service;

import com.Ezenweb.domain.dto.MemberDto;
import com.Ezenweb.domain.entity.member.MemberEntity;
import com.Ezenweb.domain.entity.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service            //해당 클래스가 Service 컴포넌트임을 명시     //1. 비즈니스 로직 [알고리즘 - 기능]
public class MemberService implements UserDetailsService, OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    //UserDetailsService : 일반회원
    //OAuth2UserService<OAuth2UserRequest, OAuth2User> : 소셜회원

    // ------------------------ 전역 객체 ------------------------------
    @Autowired
    private MemberRepository memberRepository;  //리포지토리 객체
    @Autowired  //스프링 컨테이너 [메모리]에게 위임
    private HttpServletRequest request;         //요청 객체
    @Autowired
    JavaMailSender javaMailSender;              //java mail sender 라이브러리 설치해서 사용 가능

    // ------------------------ 서비스 메소드 ------------------------------

    // ---------------- 로그인된 엔티티 호출 ------------ //
    public MemberEntity getEntity() {
        //1. 로그인 정보 확인 [세션 = loginMno]
        Object object = request.getSession().getAttribute("loginMno");
        if(object == null){ return null; }     //로그인이 안됐으면 그냥 종료

        //2. 로그인된 회원번호
        int mno = (Integer)object;

        //3. 회원번호 -> 회원정보 호출
        Optional<MemberEntity> optional = memberRepository.findById(mno);
        if(!optional.isPresent()){ return null; }
        //4. 로그인된 회원의 엔티티
        return optional.get();
    }
    // ---------------------------------------------- //

    //1. [일반회원] 회원가입
    //@Transactional  //트랜잭션
    @Transactional
    public int setmember(MemberDto memberDto ){
        // 암호화 : 해시함수 사용하는 암호화 기법중 하나 [ BCrypt ]
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setMpassword( passwordEncoder.encode( memberDto.getPassword() ) );

        // 1. DAO 처리 [ insert ]
        MemberEntity entity = memberRepository.save( memberDto.toEntity() );
        // memberRepository.save( 엔티티 객체 ) : 해당 엔티티 객체가 insert 생성된 엔티티객체 반환

        //회원등급 넣어주기
        entity.setMrol("user");

        // 2. 결과 반환 [ 생성된 엔티티의 pk값 반환 ]
        return entity.getMno();
    }

    /*//2. 로그인 [시큐리티 사용시 필요없음]
    @Transactional
    public int getmember(MemberDto memberDto) {
        //1. DAO 처리 [select]
        // 모든 엔티티 호출 [select * from member]
        List<MemberEntity> entityList = memberRepository.findAll();     //select
        //2. 입력받은 데이터와 일치값 찾기
        for(MemberEntity entity : entityList){      //리스트 반복
            if(entity.getMemail().equals(memberDto.getMemail())){
                if(entity.getMpassword().equals(memberDto.getMpassword())){
                    //세션 부여 [로그인 성공시 loginMno라는 이름으로 회원번호 세션에 저장]
                    request.getSession().setAttribute("loginMno", entity.getMno());
                    return 1;   //로그인 성공
                }else{
                    return 2;   //비밀번호가 틀림
                }
            }
        }
        return 0;               //로그인 실패
    }*/

    //2. [시큐리티 사용시] 로그인 인증 메소드 재정의 [Override]
    @Override
    public UserDetails loadUserByUsername(String memail ) throws UsernameNotFoundException {
        // 1. 입력받은 아이디 [ memail ] 로 엔티티 찾기
        MemberEntity memberEntity = memberRepository.findByMemail( memail )
                .orElseThrow( ()-> new UsernameNotFoundException("사용자가 존재하지 않습니다,") ); // .orElseThrow : 검색 결과가 없으면 화살표함수[람다식]를 이용한
        // 2. 검증된 토큰 생성
        Set<GrantedAuthority>  authorities = new HashSet<>();
        authorities.add( new SimpleGrantedAuthority(memberEntity.getMrol()) ); // 토큰정보에 일반회원 내용 넣기
        // 3.
        MemberDto memberDto = memberEntity.toDto(); // 엔티티 --> Dto
        memberDto.setAuthorities( authorities );       // dto --> 토큰 추가
        return memberDto; // Dto 반환 [ MemberDto는 UserDetails 의 구현체 ]
        // 구현체 : 해당 인터페이스의 추상메소드[선언만]를 구현해준 클래스의 객체
    }

    //3. 비밀번호 찾기
    @Transactional
    public String getpassword(String memail){
        //1. 모든 레코드/엔티티 꺼내온다
        List<MemberEntity> entityList = memberRepository.findAll();
        //2. 리스트에서 찾기
        for(MemberEntity entity : entityList){
            if(entity.getMemail().equals(memail)){
                return entity.getMpassword();
            }
        }
        return null;
    }

    //4. 회원탈퇴
    @Transactional
    public int setdelete(String mpassword){
        // ** 로그인된 회원의 엔티티 필요!! **
        //1. 세션 호출
        Object object = request.getSession().getAttribute("loginMno");
        System.out.println("세션확인1 : "+object);
        //2. 세션 확인 [만약에 세션이 null이면 0 반환]
        if(object != null){
            int mno = (Integer)object; //형변환 [object -> int]
            System.out.println("세션확인2 : "+mno);
            //3. 세션에 있는 회원번호[PK]로 리포지토리에서 찾기 [findById = select * from member where mno=?]
            Optional<MemberEntity> optional = memberRepository.findById(mno);//Optional : NullPointerException을 방지해주는 메소드 제공하는 클래스
            if(optional.isPresent()){   //optional 객체내 엔티티 존재 여부 판단해서 존재하면
                //4. Optional 객체에서 데이터[엔티티] 빼오기
                MemberEntity entity = optional.get();
                //5. 탈퇴 [delete = delete from member where mno = ?]
                memberRepository.delete(entity);
                //6. 세션 삭제 [로그아웃]
                request.getSession().setAttribute("loginMno",null);
                return 1;
            }
        }
        return 0;       //만약에 세션이 null이면 0반환 / select 실패 시 0반환
    }

    //5. 회원수정
    @Transactional  //CRUD 중에서 update 시에는 꼭 필요!
    public int setupdate(String mpassword){
        //1. 세션 호출
        Object object = request.getSession().getAttribute("loginMno");
        //2. 세션 존재여부 판단
        if(object != null){ //로그인을 했으면
            int mno = (Integer)object;  //형변환
            //3. PK값을 가지고 엔티티[레코드] 검색
            Optional<MemberEntity> optional = memberRepository.findById(mno);
            //4. 검색된 결과 여부 판단
            if(optional.isPresent()){   //엔티티가 존재하면
                MemberEntity entity = optional.get();
                //5. 찾은 엔티티[레코드]의 필드갑 ㅅ변경 [update member set 필드명=값 where 필드명=값]
                entity.setMpassword(mpassword);
                return 1;
            }
        }
        return 0;
    }

    //6. 로그인 여부 판단 메소드
    /*public int getloginMno(){
        //1. 세션 호출
        Object object = request.getSession().getAttribute("loginMno");
        //2. 세션 존재여부 판단
        if(object != null){ return (Integer) object; }
        else{ return 0; }
    }*/
    //6. 로그인 여부 판단 메소드 [principal session]
    public String getloginMno(){
        //1. 인증된 토큰 확인
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //2. 인증된 토큰 내용 확인
        Object principal = authentication.getPrincipal();//Principal : 접근주체 [UserDetails(MemberDto)]
        System.out.println("토큰 내용 확인) "+principal);
        if(principal.equals("anonymousUser")){  //anonymousUser이면 로그인 전
            return null;
        }else{                                  //anonymousUser 아니면 로그인 후
            MemberDto memberDto = (MemberDto) principal;
            //return memberDto.getMemail()+"_"+memberDto.getAuthorities();  //이런 식으로 _를 구분자로 쓰고 붙여서 반환할 수도 있다
            return memberDto.getMemail();
        }
    }

    //7. 로그아웃 [시큐리티 사용시 필요없음]
    /*@Transactional
    public void setlogout(){
        request.getSession().setAttribute("loginMno",null);
    }*/

    //8. 회원목록 서비스
    public List<MemberDto> list(){
        //1. JPA 이용한 모든 엔티티 호출
        List<MemberEntity> list = memberRepository.findAll();
        //2. 엔티티 -> DTO 변환
        List<MemberDto> dtolist = new ArrayList<>();    //dto list 선언
        for(MemberEntity entity : list){
            dtolist.add(entity.toDto());                //형변환
        }
        return dtolist;
    }

    //9. 인증코드 발송
    public String getauth(String toemail){
        String auth = "";    //인증코드
        String html = "<html><body><h1> EZENWEB 회원가입 이메일 인증코드입니다. </h1>";

        Random random = new Random();   //1. 난수 객체 선언
        for(int i=0;i<6;i++){           //2. 6번 반복
            char randchar = (char)(random.nextInt(26)+97);  //97~122 : 알파벳 소문자 범위지정
            //char randchar = (char)(random.nextInt(10)+48);  //48~57 : 0~9 범위지정
            auth += randchar;
        }
        html += "<div> 인증코드 : "+auth+" </div>";
        html += "</body></html>";
        mailsend(toemail, "Ezenweb 인증코드", html);    //메일전송
        return auth;//인증코드 반환
    }

    //*. 메일 전송 서비스
    public void  mailsend(String toemail, String title, String content){
        try {
            //1. Mime프로토콜 객체 생성
            //MimeMessage : javax.mail.MimeMessage import
            MimeMessage message = javaMailSender.createMimeMessage();
            //2. MimeMessageHelper 설정 객체 생성 new MimeMessageHelper(mime객체명,첨부파일여부,인코딩타입)
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");
            mimeMessageHelper.setFrom("qldk9603@naver.com", "Ezenweb");     //3. 보내는 사람 정보
            mimeMessageHelper.setTo(toemail);       //4. 받는 사람
            mimeMessageHelper.setSubject(title);    //5. 메일 제목
            mimeMessageHelper.setText(content, true);  //6. 메일 내용   //true: html 형식 지원
            javaMailSender.send(message);           //7. 메일 전송
        }catch (Exception e){ System.out.println("메일전송 실패) "+e); }
    }

    //로그인을 성공한 소셜 회원 정보를 받는 메소드
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        return null;
    }
}
