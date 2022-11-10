package com.Ezenweb.controller;

import com.Ezenweb.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {
    //1. p.70
    @PutMapping(value = "/member")
    public String putMember(@RequestBody Map<String, String> putData) {
        return putData.toString();
    }

    //2-1. p.71     반환타입 : 문자열[String]
    @PutMapping(value="/member1")
    public String postMemberDto(@RequestBody MemberDto dto) {
        return dto.toString();
    }

    //2-2. p.72     반환타입 : DTO[MemberDto]
    @PutMapping(value="/member2")
    @ResponseBody
    public MemberDto postMemberDto2(@RequestBody MemberDto dto) {
        return dto;
    }

}
