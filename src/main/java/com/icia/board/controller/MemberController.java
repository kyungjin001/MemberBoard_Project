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

import com.icia.board.dto.MemberDTO;
import com.icia.board.service.MemberService;

@Controller
@RequestMapping(value = "/member/*")
public class MemberController {

	@Autowired
	private MemberService ms;

	@RequestMapping(value = "insertform", method = RequestMethod.GET)
	public String insertForm() {
		return "member/insert";
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute MemberDTO members) throws IllegalStateException, IOException {

//		model.addAttribute("Member",members);
		int save = ms.insert(members);

		if (save > 0) {
			return "index";
		} else {

			return "member/insert";
		}

	}

//
	// 로그인 페이지 출력
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginForm() {
		return "member/login";
	}
	
	@RequestMapping(value = "mypage", method = RequestMethod.GET)
	public String mypage(@RequestParam("m_number") long m_number, Model model) throws IllegalStateException, IOException {
		
		MemberDTO members = ms.mypage(m_number);

		model.addAttribute("mypage", members);
		return "member/mypage";
	}
	
	

	


	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginForm(@ModelAttribute MemberDTO members) {
		String resultPage = ms.login(members);

		return resultPage;

	}

//
	// 로그아웃 처리
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		// 세션에 저장된 데이터를 지움.
		session.invalidate();
		return "index";
	}

//
//	// 회원목록 처리
	@RequestMapping(value = "findAll", method = RequestMethod.GET)
	public String findAll(Model model) {
		List<MemberDTO> mList = ms.findAll();

		model.addAttribute("mList", mList);
		return "member/findAll";
	}
	
	
	
	
//
//	// 상세조회 처리
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String findById(@RequestParam("m_number") long m_number, Model model) {

		MemberDTO members = ms.findById(m_number);

		model.addAttribute("members", members);

		return "member/detail";

	}
//
	// 삭제 처리
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(@RequestParam("m_number") long m_number) {

		ms.delete(m_number);

		return "index";
	}
//
//	// 수정화면 요청
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String updateForm(@RequestParam("m_number") long m_number, Model model) {

		MemberDTO members = ms.findById(m_number);
		model.addAttribute("members", members);
		return "member/update";
	}


//	
//	// 수정처리
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute MemberDTO members,Model model) throws IllegalStateException, IOException {

		ms.update(members);
		
		String result ="redirect:/member/mypage?m_number=" + members.getM_number();
		
		model.addAttribute("members", members);

		return result;
	}
//	
//	//아이디 중복체크
	@RequestMapping(value = "/idDuplicate", method = RequestMethod.POST)
	public @ResponseBody String idDuplicate(@RequestParam("m_id") String m_id) {

		String result = ms.idDuplicate(m_id);
		return result; // "ok" or "no"
	}
//	 
//	@RequestMapping(value = "/detailAjax", method = RequestMethod.POST)
//	public @ResponseBody MemberDTO detailAjax(@RequestParam("m_number") long m_number) {
//		MemberDTO members = ms.findById(m_number);
//		return members; 
//	}
}
