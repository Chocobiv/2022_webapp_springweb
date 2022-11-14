package com.Ezenweb.controller.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//p.49
@RestController     //현재 클래스를 스프링의 RestController 사용
public class HelloController {
    //클라이언트의 요청,응답 수행하는 공간
    //실제 데이터 처리[가공]이나 로직[기능] => DAO나 서비스에서 하자

    @RequestMapping("/")
    public String index(){ return "메인페이지"; }

    @RequestMapping("/hello")
    public String Hello(){
        return "Hello World";
    }


}
