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


    //1. 방명록 페이지 열기
    @GetMapping("/")
    public Resource getvisitlog() {return new ClassPathResource("templates/visitlog/visitlog.html");}

    //2. 카테고리 등록
    @PostMapping("/setvcategory")
    public boolean setvcategory(@RequestBody VcategoryDto vcategoryDto){
        return visitlogService.setvcategory(vcategoryDto);
    }

    //3. 모든 카테고리 출력
    @GetMapping("/getvcategory")
    public List<VcategoryDto> getvcategory(){
        return visitlogService.getvcategory();
    }

    //4. 방명록 등록
    @PostMapping("/setvisitlog")
    public boolean setvisitlog(VisitlogDto visitlogDto){
        System.out.printf("확인 : "+visitlogDto.toString());
        return visitlogService.setvisitlog(visitlogDto);
    }

    //5. 방명록 출력 [전체보기/카테고리별]
    @GetMapping("/getvisitlog")
    public List<VisitlogDto> getvisit(@RequestParam("vcno") int vcno){
        return visitlogService.getvisitlog(vcno);
    }

    //6. 방명록 수정을 위한 방명록 불러오기
    @GetMapping("/viewvisitlog")
    public VisitlogDto viewvisitlog(@RequestParam("vno") int vno){ return visitlogService.viewvisitlog(vno); }

    //7. 방명록 수정
    @PutMapping("/updatevisitlog")
    public boolean updatevisit(@RequestBody VisitlogDto visitlogDto){
        return visitlogService.updatevisitlog(visitlogDto);
    }

    //8. 방명록 삭제
    @DeleteMapping("/deletevisitlog")
    public boolean deletevisitlog(@RequestParam("vno") int vno){
        return visitlogService.deletevisitlog(vno);
    }

    //9. 첨부파일 다운로드
    @GetMapping("/filedownload")
    public void filedownload(@RequestParam("filename") String filename){ visitlogService.filedownload(filename); }
}
