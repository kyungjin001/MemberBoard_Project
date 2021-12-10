package com.icia.board.service;

import java.io.IOException;
import java.util.List;

import com.icia.board.dto.CommentDTO;

public interface CommentService {

	void save(CommentDTO comment);


	List<CommentDTO> findAll(long b_number);
	
	
	public String delete(long c_number, long b_number, int page);
	
	
	public void update(CommentDTO comment);

}
