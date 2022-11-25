package com.Ezenweb.domain.entity.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Integer> {
    //1. 기본 메소드 외 메소드 추가
    @Query(value = "select * from board where bcno = :bcno", nativeQuery=true)//: 변수를 뜻함
    //@Query(value = "select b from board b where b.bcno = ?1")//: 변수를 뜻함
    Page<BoardEntity> findByBcno(@Param("bcno") int bcno, Pageable pageable);    //카테고리번호를 찾기위한 메소드 생성
        //Page 사용하는 이유 : 페이징처리 하기 위해서

}
