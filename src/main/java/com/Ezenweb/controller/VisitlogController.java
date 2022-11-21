package com.Ezenweb.controller;

import com.Ezenweb.domain.dto.VcategoryDto;
import com.Ezenweb.domain.dto.VisitlogDto;
import com.Ezenweb.service.VisitlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitlog")
public class VisitlogController {
    @Autowired
    private VisitlogService visitlogService;


    //방명록 페이지 열기
    @GetMapping("/")
    public Resource getvisitlog() {return new ClassPathResource("templates/visitlog/visitlog.html");}

    //카테고리 등록
    @PostMapping("/setvcategory")
    public boolean setvcategory(@RequestBody VcategoryDto vcategoryDto){
        return visitlogService.setvcategory(vcategoryDto);
    }

    //모든 카테고리 출력
    @GetMapping("/getvcategory")
    public List<VcategoryDto> getvcategory(){
        return visitlogService.getvcategory();
    }

    //방명록 등록
    @PostMapping("/setvisitlog")
    public boolean setvisitlog(@RequestBody VisitlogDto visitlogDto){
        return visitlogService.setvisitlog(visitlogDto);
    }

    @GetMapping("/getvisitlog")
    public List<VisitlogDto> getvisit(@RequestParam("vcno") int vcno){
        return visitlogService.getvisitlog(vcno);
    }
}
