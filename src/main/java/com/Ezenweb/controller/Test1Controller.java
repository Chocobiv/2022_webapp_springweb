package com.Ezenweb.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController             //해당 클래스가 controller임을 명시 [스프링부트]
@RequestMapping("/test1")   //URL 경로 정의     http://localhost:8081/test1
public class Test1Controller {
    /*
    //반환타입 : 문자열    String
    //1. text.html 열어보자
    @GetMapping("")                    //http://localhost:8081/test1
    public String gettext(){
        return "test1.html";
    }
    */

    //반환타입 : HTML     템플릿(외부적인 기능 없이 html 반환)
    //반환타입 : Resource
    //반환값 : new ClassPathResource("html경로")
    //경로는 프로젝트 내 resource 폴더부터 시작
    //import org.springframework.core.io.Resource;
    //1. text.html 열어보자
    @GetMapping("")                    //http://localhost:8081/test1
    public Resource gettext(){
        return new ClassPathResource("templates/test1.html");
    }



}
