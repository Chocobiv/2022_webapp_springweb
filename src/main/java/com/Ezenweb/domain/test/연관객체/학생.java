package com.Ezenweb.domain.test.연관객체;

import javax.persistence.ManyToOne;

public class 학생 {
    String 학생명;
    @ManyToOne
    학급 학급;
}
