package com.Ezenweb.service;

import com.Ezenweb.domain.dto.VcategoryDto;
import com.Ezenweb.domain.dto.VisitlogDto;
import com.Ezenweb.domain.entity.vcategory.VcategoryEntity;
import com.Ezenweb.domain.entity.vcategory.VcategoryRepository;
import com.Ezenweb.domain.entity.visitlog.VisitlogEntity;
import com.Ezenweb.domain.entity.visitlog.VisitlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VisitlogService {
    @Autowired
    private VcategoryRepository vcategoryRepository;
    @Autowired
    private VisitlogRepository visitlogRepository;
    @Autowired
    private HttpServletResponse response;

    String path = "C:\\Users\\504\\IdeaProjects\\2022_webapp_springweb\\src\\main\\resources\\static\\vupload\\";


    // --------------------- 2. 서비스 ------------------------- //

    //카테고리 등록
    public boolean setvcategory(VcategoryDto vcategoryDto){
        VcategoryEntity vcategoryEntity = vcategoryRepository.save(vcategoryDto.toEntity());
        if(vcategoryEntity.getVcno() != 0) return true;
        else return false;
    }

    //카테고리 출력
    public List<VcategoryDto> getvcategory(){
        List<VcategoryEntity> entityList = vcategoryRepository.findAll();
        List<VcategoryDto> dtoList = new ArrayList<>();
        entityList.forEach(e -> dtoList.add(e.toDto()));
        return dtoList;
    }

    //방명록 등록
    @Transactional
    public boolean setvisitlog(VisitlogDto visitlogDto){
        //1. 선택한 카테고리 번호로 카테고리 엔티티 검색
        Optional<VcategoryEntity> optional = vcategoryRepository.findById(visitlogDto.getVcno());
        if(!optional.isPresent()) return false;
        VcategoryEntity vcategoryEntity = optional.get();

        VisitlogEntity visitlogEntity = visitlogRepository.save(visitlogDto.toEntity());
        if(visitlogEntity.getVno() != 0) {  //2. 생성된 entity의 방명록번호가 0이 아니면 성공

            if(visitlogDto.getVfile() != null){ //첨부파일이 null이 아니면
                String uuid = UUID.randomUUID().toString();//1. 난수생성
                String filename = uuid + "_" + visitlogDto.getVfile().getOriginalFilename();//2. 난수_파일명
                visitlogEntity.setVfile(filename);//3. 엔티티에 저장

                // 첨부파일 업로드
                try {
                    File uploadfile = new File(path+filename);  //4. 경로+파일명 객체화
                    visitlogDto.getVfile().transferTo(uploadfile);//5. visitlogDto.getVfile()을 해당 경로로 업로드
                }catch(Exception e) {
                    System.out.println("첨부파일 업로드 실패");
                }
            }

            visitlogEntity.setVcategoryEntity(vcategoryEntity);
            vcategoryEntity.getVisitlogEntityList().add(visitlogEntity);
            return true;
        }else return false;
    }

    //방명록 전체 출력과 카테고리별 출력
    public List<VisitlogDto> getvisitlog(int vcno){
        List<VisitlogEntity> entitylist = null;
        if(vcno == 0){ entitylist = visitlogRepository.findAll(); }
        else {
            VcategoryEntity vcEntity = vcategoryRepository.findById(vcno).get();
            entitylist = vcEntity.getVisitlogEntityList();  //해당 엔티티의 방명록 목록
        }
        List<VisitlogDto> visitlogDtoList = new ArrayList<>();
        for(VisitlogEntity entity : entitylist){
            visitlogDtoList.add(entity.toDto());
        }
        return visitlogDtoList;
    }

    //6. 방명록 수정을 위한 방명록 불러오기
    public VisitlogDto viewvisitlog(int vno){
        VisitlogDto vcDto = visitlogRepository.findById(vno).get().toDto();
        return vcDto;
    }

    @Transactional
    //7. 방명록 수정
    public boolean updatevisitlog(VisitlogDto visitlogDto){
        Optional<VisitlogEntity> optional = visitlogRepository.findById(visitlogDto.getVno());
        if(optional.isPresent()){
            VisitlogEntity entity = optional.get();
            entity.setVwriter(visitlogDto.getVwriter());
            entity.setVcontent(visitlogDto.getVcontent());
            return true;
        }else return false;
    }

    //8. 방명록 삭제
    public boolean deletevisitlog(int vno){
        Optional<VisitlogEntity> optional = visitlogRepository.findById(vno);
        if(optional.isPresent()){
            VisitlogEntity entity = optional.get();
            visitlogRepository.delete(entity);
            return true;
        }else return false;
    }

    // 첨부파일 다운로드
    public void filedownload(String filename){
        //1. uuid 제거
        String realfilename = "";
        String[] split = filename.split("_");
        for(int i=1; i<split.length; i++){
            realfilename += split[i];
            if(split.length-1 != i){    //마지막 인덱스가 아니면
                realfilename += "_";
            }
        }
        //2. 경로 찾기
        String filepath = path+filename;
        //3. 헤더 구성 [HTTP에서 지원하는 다운로드형식 메소드 - response]
        try {
            response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(realfilename,"UTF-8"));
            File file = new File(filepath);
            //4. 다운로드 스트림
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[(int)file.length()];
            bis.read(bytes);
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            bos.write(bytes);
            bos.flush();
            bos.close();
            bis.close();
        }catch(Exception e){ System.out.println(e); }
    }
}
