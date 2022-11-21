package com.Ezenweb.domain.dto;

import com.Ezenweb.domain.entity.vcategory.VcategoryEntity;
import lombok.*;


@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class VcategoryDto {

    private int vcno;       //카테고리 번호 [PK]
    private String vcname;  //카테고리 이름

    public VcategoryEntity toEntity(){
        return VcategoryEntity.builder()
                .vcno(this.vcno)
                .vcname(this.vcname)
                .build();
    }
}
