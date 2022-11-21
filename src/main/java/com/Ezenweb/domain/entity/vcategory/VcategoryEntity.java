package com.Ezenweb.domain.entity.vcategory;

import com.Ezenweb.domain.dto.VcategoryDto;
import com.Ezenweb.domain.entity.visitlog.VisitlogEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="vcategory")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class VcategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vcno;       //카테고리 번호 [PK]
    private String vcname;  //카테고리 이름

    @OneToMany(mappedBy = "vcategoryEntity")
    @Builder.Default
    private List<VisitlogEntity> visitlogEntityList = new ArrayList<>();

    public VcategoryDto toDto(){
        return VcategoryDto.builder()
                .vcno(this.vcno)
                .vcname(this.vcname)
                .build();
    }
}
