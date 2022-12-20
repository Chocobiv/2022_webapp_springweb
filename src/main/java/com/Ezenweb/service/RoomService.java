package com.Ezenweb.service;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RoomService {

    @Transactional
    public boolean write(){
        return true;
    }
}
