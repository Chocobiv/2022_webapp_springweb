package com.Ezenweb.domain.entity.room;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "roomimg")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RoomImgEntity {
    @Id         //엔티티당 무조건 1개이상 [PK]
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 번호 부여
    private int rimgno;//번호
    private String rimg;//경로

    @ManyToOne
    @JoinColumn(name="rno")
    @ToString.Exclude
    private RoomEntity roomEntity;
}
