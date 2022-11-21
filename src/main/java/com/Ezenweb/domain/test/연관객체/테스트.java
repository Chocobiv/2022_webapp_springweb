package com.Ezenweb.domain.test.연관객체;

public class 테스트 {
    public static void main(String[] args) {
        //1. 학급 객체 생성
        학급 학급1 = new 학급();
        학급1.학급명 = "초등학생";

        제품 제품1 = new 제품();
        제품1.제품명 = "초콜렛";

        //2. 학생 객체 생성
        학생 학생1 = new 학생();
        학생1.학생명 = "유재석";

        이미지 이미지1 = new 이미지();
        이미지1.이미지명 = "img1";
        이미지 이미지2 = new 이미지();
        이미지2.이미지명 = "img1";

        //3. 단방향 설정
        학생1.학급 = 학급1;

        이미지1.제품 = 제품1;
        이미지2.제품 = 제품1;

        //4. 양방향 설정
        학급1.학생리스트.add(학생1);

        제품1.이미지리스트.add(이미지1);
        제품1.이미지리스트.add(이미지2);

        //5. JOIN 기능
            //1. 학급에서 학생 조회 가능
        System.out.println(학급1.학생리스트.get(0).학생명);
            //2. 학생이 학급 조회 가능
        System.out.println(학생1.학급.학급명);

        System.out.println(제품1.이미지리스트.toString());
        System.out.println(이미지1.제품.제품명);

    }
}
