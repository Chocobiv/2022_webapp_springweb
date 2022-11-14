package com.Ezenweb.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository     //해당 인터페이스가 레포지토리임을 명시
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

}
