package com.Ezenweb.controller;

import com.Ezenweb.domain.dto.BcategoryDto;
import com.Ezenweb.service.BcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bcategory")
public class BcategoryController {
    // --------------------- 1. 전역변수 ------------------------- //
    @Autowired
    private BcategoryService bcategoryService;


    // ------------------- 2. 페이지[html] 요청 로드 [view] ------------------- //

    // --------------------- 3. 요청과 응답 처리 [model] --------------------- //
    //1. 카테고리 등록
    @PostMapping("/setbcategory")
    public boolean setBcategory(@RequestBody BcategoryDto bcategoryDto){
        return bcategoryService.setBcategory(bcategoryDto);
    }


}
