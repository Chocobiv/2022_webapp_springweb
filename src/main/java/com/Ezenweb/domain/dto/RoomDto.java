package com.Ezenweb.domain.dto;

import com.Ezenweb.domain.entity.room.RoomEntity;
import com.Ezenweb.domain.entity.room.RoomImgEntity;
import com.Ezenweb.domain.entity.room.RoomImgRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class RoomDto {
    private int rno;
    private String rtitle;
    private int rprice;
    private String rtrans;
    private List<MultipartFile> rimg;   //저장용 이미지 //첨부파일 여러개를 위한 List 자료형 사용
    private String rname;    //주소이름
    private String rlat;     //위도
    private String rlng;     //경도

    private String memail;          //출력용 작성자
    private List<String> getrimg;   //출력용 이미지

    /*@Autowired
    private RoomImgRepository roomImgRepository;*/

    //save 용도로 많이 쓰이기 때문에 img 반복문 돌려서 이미지 업로드
    public RoomEntity toEntity() {
        //이미지 리스트
        /*ArrayList<RoomImgEntity> list = new ArrayList<>();
        for(MultipartFile file : rimg){
            if(!file.getOriginalFilename().equals("")){
                RoomImgEntity roomImgEntity = new RoomImgEntity();
                roomImgEntity.setRimg(file.getOriginalFilename());
                list.add(roomImgRepository.save(roomImgEntity));//이미지 업로드
            }
        }*/

        return RoomEntity.builder()
                .rtitle(this.rtitle)
                .rprice(this.rprice)
                .rtrans(this.rtrans)
                .rname(this.rname)
                .rlat(this.rlat)
                .rlng(this.rlng)
                .build();
    }
}
