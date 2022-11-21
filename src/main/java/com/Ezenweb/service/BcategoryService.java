package com.Ezenweb.service;

import com.Ezenweb.domain.entity.bcategory.BcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BcategoryService {
    // --------------------- 1. 전역변수 ------------------------- //
    @Autowired
    private BcategoryRepository bcategoryRepository;

    // --------------------- 2. 서비스 ------------------------- //

}
