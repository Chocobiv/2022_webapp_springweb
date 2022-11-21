package com.Ezenweb.domain.dto;

import com.Ezenweb.domain.entity.visitlog.VisitlogEntity;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class VisitlogDto {
    private int vno;            //방명록 번호
    private String vcontent;    //방명록 내용
    private String vwriter;     //방명록 작성자

    private int vcno;           //카테고리 번호 [FK]

    public VisitlogEntity toEntity(){
        return VisitlogEntity.builder()
                .vno(this.vno)
                .vcontent(this.vcontent)
                .vwriter(this.vwriter)
                .build();

    }
}
