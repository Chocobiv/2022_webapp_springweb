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

    //Page 사용하는 이유 : 페이징처리 하기 위해서
    /*
    //1. 제목검색
    @Query(value = "select * from board where bcno= :bcno and btitle like %:keyword%", nativeQuery = true)
    Page<BoardEntity> findByBtitle(int bcno, String keyword, Pageable pageable);

    //2. 내용검색
    @Query(value = "select * from board where bcno= :bcno and bcontent like %:keyword%", nativeQuery = true)
    Page<BoardEntity> findByBcontent(int bcno, String keyword, Pageable pageable);

    //3. 검색이 없을 때
    @Query(value = "select * from board where bcno = :bcno", nativeQuery=true)//: 변수를 뜻함
        //@Query(value = "select b from board b where b.bcno = ?1")//: 변수를 뜻함
    Page<BoardEntity> findByBcno(@Param("bcno") int bcno, Pageable pageable);    //카테고리번호를 찾기위한 메소드 생성*/

    //1~3 통합
    @Query(value = "SELECT * FROM board WHERE IF( :bcno=0, bcno like '%%', bcno = :bcno ) " +
            "and IF( :key = '', true, IF(:key='btitle', btitle like %:keyword%, bcontent like %:keyword%) )", nativeQuery = true)  //nativeQuery : 실제 해당 SQL의 질의어 사용하겠다
    Page<BoardEntity> findBySearch(int bcno, String key, String keyword, Pageable pageable);
}
