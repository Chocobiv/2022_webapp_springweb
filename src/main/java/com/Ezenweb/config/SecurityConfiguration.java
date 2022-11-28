package com.Ezenweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override   //재정의 [상속받은 클래스로부터 메소드 재구현]
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);  //super : 상속클래스     // 기본값 : 모든 HTTP 보안 설정

    }

}


