package com.Ezenweb.domain.entity.board;

import com.Ezenweb.domain.entity.board.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Integer> {
}
