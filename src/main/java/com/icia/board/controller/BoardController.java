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



// /board/* 주소를 모두 처리
@Controller
@RequestMapping(value = "/board/*") // /board/* 로 요청하는 주소는 다 받겠다
public class BoardController {

	@Autowired
	private BoardService bs;
	@Autowired
	private CommentService cs;

	// 글쓰기를 처리하는 메서드
	@RequestMapping(value = "save", method = RequestMethod.GET)
	public String saveForm() {
		// view/board 폴더에 있는 save.jsp를 출력
		return "board/save";
	}



	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@ModelAttribute BoardDTO board) throws IllegalStateException, IOException {
		bs.save(board);
		return "redirect:/board/paging";
	}
	
	

//	
////
////	// 글목록 처리
////
	@RequestMapping(value = "findAll", method = RequestMethod.GET)
	public String findAll(Model model) {
		List<BoardDTO> bList = bs.findAll();

		model.addAttribute("bList", bList);
		return "/board/findAll";
	}
	
	
	
	
//
//	// 상세조회 처리
//
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String findById(@RequestParam("b_number") long b_number, 
			Model model,@RequestParam(value="page", required=false, defaultValue="1")int page
			) {

		BoardDTO board = bs.findById(b_number);

		model.addAttribute("board", board);
		model.addAttribute("page", page); //detail.jsp로 갈 때 page 값을 가지고 감.
		List<CommentDTO> commentList = cs.findAll(b_number);
		model.addAttribute("commentList", commentList);
		return "/board/detail";

	}
////
////	// 삭제 처리
////
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(@RequestParam("b_number") long b_number) {

		bs.delete(b_number);

		return "redirect:/board/paging";	
		}
////
////	// 수정화면 요청

	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String updateForm(@RequestParam("b_number") long b_number, Model model,@RequestParam(value="page", required=false, defaultValue="1")int page) {

		BoardDTO board = bs.findById(b_number);
		model.addAttribute("board", board);
		model.addAttribute("page", page); //detail.jsp로 갈 때 page 값을 가지고 감.
		return "board/update";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute BoardDTO board, @RequestParam(value="page", required=false, defaultValue="1")int page) throws IllegalStateException, IOException 
			{
		bs.update(board);

		return "redirect:/board/detail?b_number="+board.getB_number()+ "&page=" + page;	
		}
//	
//	
	@RequestMapping(value="paging", method=RequestMethod.GET)
	// value: 파라미터이름, required: 필수여부, defaultValue: 기본값(page 요청값이 없으면 1로 세팅)
	public String paging(@RequestParam(value="page", required=false, defaultValue="1")int page, Model model) {
		List<BoardDTO> bList = bs.pagingList(page);
		PageDTO paging = bs.paging(page);
		model.addAttribute("bList", bList);
		model.addAttribute("paging", paging);
		return "board/findAll";
	}
//	
	@RequestMapping(value="search", method=RequestMethod.GET)
	public String search(@RequestParam("searchtype") String searchtype, @RequestParam("keyword") String keyword, Model model) {
		List<BoardDTO> bList = bs.search(searchtype, keyword);
		model.addAttribute("bList", bList);
		System.out.println(bList);
		return "board/findAll";
	}
	
	
	  
}
