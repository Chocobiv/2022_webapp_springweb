package com.Ezenweb.domain.entity.visitlog;

import com.Ezenweb.domain.dto.VisitlogDto;
import com.Ezenweb.domain.entity.vcategory.VcategoryEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "visitlog")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class VisitlogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vno;            //방명록 번호
    private String vcontent;    //방명록 내용
    private String vwriter;     //방명록 작성자

    @ManyToOne
    @JoinColumn(name = "vcno")
    @ToString.Exclude
    private VcategoryEntity vcategoryEntity;

    public VisitlogDto toDto() {
        return VisitlogDto.builder()
                .vno(this.vno)
                .vcontent(this.vcontent)
                .vwriter(this.vwriter)
                .build();
    }
}
