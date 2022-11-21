package com.Ezenweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing      //JPA 감시 [생성, 변경] p.242
public class Webstart {
    public static void main(String[] args) {
        // main 스레드
        SpringApplication.run(Webstart.class);
    }
}
