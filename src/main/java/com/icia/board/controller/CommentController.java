package com.icia.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icia.board.dto.BoardDTO;
import com.icia.board.dto.CommentDTO;
import com.icia.board.dto.PageDTO;
import com.icia.board.service.BoardService;
import com.icia.board.service.CommentService;

// /comment/* 주소를 모두 처리
@Controller
@RequestMapping(value = "/comment/*")
public class CommentController {

	@Autowired
	private CommentService cs;

	
	// 댓글 저장 처리
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody List<CommentDTO> save(@ModelAttribute CommentDTO comment) {
		cs.save(comment);
		List<CommentDTO> commentList = cs.findAll(comment.getB_number());
		return commentList;

	}

	// 댓글 삭제 처리
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(@RequestParam("b_number") long b_number, @RequestParam("c_number") long c_number,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		String result = cs.delete(b_number, c_number, page);
		return result;

	}

}
