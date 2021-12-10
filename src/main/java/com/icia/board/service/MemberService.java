package com.icia.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.icia.board.dto.MemberDTO;
import com.icia.board.repository.MemberRepository;


@Service
public class MemberService {

	
	
	@Autowired // 어노테이션은 밑에 한줄만 적용가능, 어노테이션 밑에 두 줄 불가능
	private MemberRepository mr;
	
//	
	@Autowired
	private HttpSession session;

	
	
	// 회원가입 처리
	public int insert(MemberDTO members) throws IllegalStateException, IOException {
		
		
		MultipartFile m_file = members.getM_file();
		String m_filename = m_file.getOriginalFilename();
		m_filename = System.currentTimeMillis() + "-" + m_filename;
		String savePath = "C:\\development\\source\\spring_kkj\\MemberBoard\\src\\main\\webapp\\resources\\upload\\"+m_filename;
		if(!m_file.isEmpty()) {
			m_file.transferTo(new File(savePath));
		}		
		members.setM_filename(m_filename);
				
		
		int result = mr.insert(members);
		
		
		return result;

		
	}
//
//
//	
	
	// 로그인 처리
	public String login(MemberDTO members) {
		
		MemberDTO loginMember = mr.login(members);
		
		if (loginMember != null) {
			//	 로그인 성공(로그인정보(아이디를)세션에 저장)
			session.setAttribute("loginId", members.getM_id());
			session.setAttribute("loginNumber", loginMember.getM_number());

			return "redirect:/board/paging"; // jsp가 아니고 주소로 받을때는 리다이렉트 필수!
		} else {
			//	 로그인 실패
			return "/member/login";
			
		}	}

	
	//회원전체목록
	public List<MemberDTO> findAll(){
		List<MemberDTO> mList = mr.findAll();
		for(MemberDTO m : mList)
		System.out.println(m);
		return mList;
	}
	
	
	//상세조회
	public MemberDTO findById(long m_number) {
		MemberDTO members = mr.findById(m_number);

		return members;
	}
	

//
//	
//	
	public void delete(long m_number) {
		mr.delete(m_number);
		
		}
//
	public MemberDTO update(MemberDTO members) throws IllegalStateException, IOException {
		MultipartFile m_file = members.getM_file();
		String m_filename = m_file.getOriginalFilename();
		m_filename = System.currentTimeMillis() + "-" + m_filename;
		String savePath = "C:\\development\\source\\spring_kkj\\MemberBoard\\src\\main\\webapp\\resources\\upload\\"+m_filename;
		if(!m_file.isEmpty()) {
			m_file.transferTo(new File(savePath));
		}		
		members.setM_filename(m_filename);
		mr.update(members);
		return members;
	}
//
//
	
	//아이디 중복체크
	public String idDuplicate(String m_id) {
		String result=mr.idDuplicate(m_id);
		if (result==null)
			return "ok"; //조회결과가 없기 때문에 해당 아이디는 사용 가능
		else
			return "no"; //조회결과가 있기 때문에 해당 아이디는 사용 불가능
	}

	public MemberDTO mypage(long m_number) {
		MemberDTO members = mr.mypage(m_number);
		return members;
	}




	
	
}
