package com.Ezenweb.controller;

import org.springframework.web.bind.annotation.*;

//p.75
@RestController     //ResponseBody 생략 가능
@RequestMapping("/api/v1/delete-api")
public class DeleteController {

    //1. p.76
    @DeleteMapping("/{variable}")
    public String deleteVariable(@PathVariable("variable") String variable) {
        return variable;
    }

    //2. p.76
    @DeleteMapping("/request1")
    public String getRequestParam1(@RequestParam("variable") String variable) {
        return variable;
    }
}
