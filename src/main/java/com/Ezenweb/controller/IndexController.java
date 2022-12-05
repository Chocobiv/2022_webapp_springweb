package com.Ezenweb.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    /*@GetMapping("/")    //http://localhost:8081 -> 도메인 구매시 www.ezenweb.com
    //index 페이지 열기 [HTML] 열기
    public Resource getindex() {
        return new ClassPathResource("templates/index.html");
    }*/
}
