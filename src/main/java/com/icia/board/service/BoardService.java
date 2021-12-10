package com.icia.board.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.board.dto.PageDTO;
import com.icia.board.dto.BoardDTO;
import com.icia.board.dto.CommentDTO;
import com.icia.board.repository.BoardRepository;


public interface BoardService {
	
	
	// interface
	// 추상메서드를 가짐. => 실행블록은 없고 메서드 선언부만 있는 것
	// interface를 구현(implements)하는 구현 클래스에서 실행블록을 작성함.
	
	
	
//

//
	
	
	
	public void save(BoardDTO board) throws IllegalStateException, IOException;
		
	
	
	public List<BoardDTO> findAll();
//
////
	public BoardDTO findById(long b_number);
////
	public void delete(long m_number);
////
	public void update(BoardDTO board) throws IllegalStateException, IOException;
//


	public PageDTO paging(int page);
//
//
//
	public List<BoardDTO> pagingList(int page);
//
//
//
	public List<BoardDTO> search(String searchtype, String keyword);
//
//
//
//
//
//
	public void save(CommentDTO comment);

}
