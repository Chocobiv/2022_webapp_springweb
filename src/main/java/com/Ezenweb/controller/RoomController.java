package com.Ezenweb.controller;

import com.Ezenweb.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class RoomController {

    // [주의] @Autowired : spring beans 에 등록된 클래스만 가능함
    @Autowired
    private RoomService roomService;

    // [주의] 리액트 라우터에 있는 path의 주소가 같으면 오류 발생
    @PostMapping("/setRoom")
    public boolean write(){
        return roomService.write();
    }
}
