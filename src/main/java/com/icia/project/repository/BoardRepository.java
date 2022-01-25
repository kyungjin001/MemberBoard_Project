package com.icia.project.repository;

import com.icia.project.dto.BoardSearchDTO;
import com.icia.project.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByBoardTitleContaining(String keyword);

    List<BoardEntity> findByBoardWriterContaining(String Title);





    // native query
    // jpql(java persistence query language)
    // 반드시 테이블에 대한 약칭을 써야 함
    // query dsl
    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits = b.boardHits+1 where b.id = :boardId")
    int boardHits(@Param("boardId") Long boardId);

}
