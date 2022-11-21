package com.Ezenweb.domain.entity.vcategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VcategoryRepository extends JpaRepository<VcategoryEntity,Integer> {
}
