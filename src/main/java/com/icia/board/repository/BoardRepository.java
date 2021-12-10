package com.icia.board.repository;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.board.dto.BoardDTO;
import com.icia.board.dto.CommentDTO;

@Repository
public class BoardRepository {

	@Autowired
	private SqlSessionTemplate sql;

	// 글작성
	public void save(BoardDTO board) {
		sql.insert("Board.save", board);
	}

	// 글목록
	public List<BoardDTO> findAll() {
		return sql.selectList("Board.findAll");
	}

	// 글상세조회
	public BoardDTO findById(long b_number) {
		sql.update("Board.updates", b_number);
		return sql.selectOne("Board.findById", b_number);
	}

	// 글삭제
	public void delete(long b_number) {
		sql.delete("Board.delete", b_number);
	}

	// 글수정
	public void update(BoardDTO board) {
		sql.update("Board.update", board);
	}

	// 페이지 개수 세기
	public int boardCount() {
		return sql.selectOne("Board.count");
	}

	// 페이징리스트1
	public List<BoardDTO> pagingList1(Map<String, Integer> pagingParam) {
		return sql.selectList("Board.pagingList1", pagingParam);
	}

	// 페이징리스트
	public List<BoardDTO> pagingList(int pagingStart) {
		return sql.selectList("Board.pagingList", pagingStart);
	}

	// 검색기능
	public List<BoardDTO> search(Map<String, String> searchParam) {
		return sql.selectList("Board.search", searchParam);
	}

	// 댓글작성 기능
	public List<BoardDTO> save(CommentDTO comment) {
		return sql.selectList("Board.save", comment);
	}

}
