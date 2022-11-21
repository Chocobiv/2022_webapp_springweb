package com.Ezenweb.domain.entity.visitlog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitlogRepository extends JpaRepository<VisitlogEntity,Integer> {
}
