package com.Ezenweb.controller;

import com.Ezenweb.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/get-api")      //요청 url 정의하기
public class GetController {
    //해당 클래스 접근[요청/호출]
    //해당 클래스내 메소드 호출 : http://localhost:8081/api/v1/get-api/hello
    //1.  p.57
    @RequestMapping(value = "/hello", method = RequestMethod.GET)   //get 요청
    public String getHello() {
        return "해당 메소드로 들어왔어!";     //response 응답
    }

    //2.  p.58
    @GetMapping("/name")       //http://localhost:8081/api/v1/get-api/name
    public String getName(){
        return "Flature";
    }

    //3.  p.59      [주의] @GetMapping 경로상의 변수명[ {variable} ]과 @PathVariable에서 빼오는 매개변수[ variable ]을 동일하게 해야함!
    @GetMapping("/variable1/{variable}")    //경로상의 변수[값] 넣기
    public String getVariable1(@PathVariable String variable){
        return variable;
    }

    //4.  p.60      만약에 변수명 동일하게 맞추지 못했다면?
    //http://localhost:8081/api/v1/get-api/variable2/하하하
    @GetMapping("/variable2/{variable}")
    public String getVariable2(@PathVariable( value = "variable" ) String test){
        return test;
    }

    //4-2.  [비교]
    //http://localhost:8081/api/v1/get-api/variable3?variable=히히
    @GetMapping("/variable3")
    public String getVariable3(@RequestParam String variable){
        return variable;
    }

    //5.  p.61
    @GetMapping("/request1")
    public String getRequestParam1( @RequestParam String name, @RequestParam String email, @RequestParam String organization){
        return name +" "+ email +" "+ organization;
    }

    //6.  p.62
    //http://localhost:8081/api/v1/get-api/request2?name=qwe&email=aa@abc.com&organization=qweqweqwe
    @GetMapping("/request2")
    public String getRequestParam2( @RequestParam Map<String,String> param){
        return param.toString();
    }

    //7.  p.66
    @GetMapping("/request3")
    public String getRequestParam3(MemberDto dto){
        return dto.toString();
    }
}
