package com.Ezenweb.domain.test.연관객체;

import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@ToString
public class 제품 {
    @Id
    String 제품명;
    @OneToMany  //주인명
    @ToString.Exclude
    List<이미지> 이미지리스트 = new ArrayList<>();
}
