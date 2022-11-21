package com.Ezenweb.service;

import com.Ezenweb.domain.dto.VcategoryDto;
import com.Ezenweb.domain.dto.VisitlogDto;
import com.Ezenweb.domain.entity.vcategory.VcategoryEntity;
import com.Ezenweb.domain.entity.vcategory.VcategoryRepository;
import com.Ezenweb.domain.entity.visitlog.VisitlogEntity;
import com.Ezenweb.domain.entity.visitlog.VisitlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VisitlogService {
    @Autowired
    private VcategoryRepository vcategoryRepository;
    @Autowired
    private VisitlogRepository visitlogRepository;

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
        System.out.println("vcategoryEntity) "+vcategoryEntity);

        VisitlogEntity visitlogEntity = visitlogRepository.save(visitlogDto.toEntity());
        System.out.println("getVno) "+visitlogEntity.getVno());
        if(visitlogEntity.getVno() != 0) {  //2. 생성된 entity의 방명록번호가 0이 아니면 성공
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
}
