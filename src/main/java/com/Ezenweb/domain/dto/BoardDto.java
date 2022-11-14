package com.Ezenweb.domain.dto;

import lombok.*;

//lombok: 생성자, Get/Set, ToString, 빌더패턴
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString @Builder
public class BoardDto {
    private int bno;
    private String btitle;
    private String bcontent;
}
