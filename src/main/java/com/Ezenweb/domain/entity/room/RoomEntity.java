package com.Ezenweb.domain.entity.room;

import com.Ezenweb.domain.dto.RoomDto;
import com.Ezenweb.domain.entity.member.MemberEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class RoomEntity {
    @Id         //엔티티당 무조건 1개이상 [PK]
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 번호 부여
    private int rno;
    @Column
    private String rtitle;
    @Column
    private int rprice;
    @Column
    private String rtrans;
    @Column
    private String rname;    //주소이름
    @Column
    private String rlat;     //위도
    @Column
    private String rlng;     //경도

    @ManyToOne
    @JoinColumn(name="mno")
    @ToString.Exclude
    private MemberEntity memberEntity;//작성자 엔티티

    @OneToMany(mappedBy="roomEntity")
    @Builder.Default
    @ToString.Exclude
    private List<RoomImgEntity> roomImgEntityList = new ArrayList<>();

    public RoomDto toDto() {
        //이미지엔티티에서 이미지 이름만 추출
        List<String> list = new ArrayList<>();
        roomImgEntityList.forEach((img)->{
            list.add(img.getRimg());
        });

        return RoomDto.builder()
                .rno(this.rno)
                .rtitle(this.rtitle)
                .rprice(this.rprice)
                .rtrans(this.rtrans)
                .rname(this.rname)
                .rlat(this.rlat)
                .rlng(this.rlng)
                .memail(this.getMemberEntity().getMemail())// memberEntity->작성자
                .getrimg(list)//imgEntityList->이미지 이름들
                .build();
    }
}
