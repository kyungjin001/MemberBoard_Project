package com.icia.board.repository;

import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.board.dto.MemberDTO;


@Repository
public class MemberRepository {

	@Autowired
	private SqlSessionTemplate sql;

	//회원 가입 처리
	public int insert(MemberDTO members) {
		return sql.insert("Member.insertMember", members);	
	}

	//회원 로그인
	public MemberDTO login(MemberDTO members) {
//		System.out.println(check);
		return sql.selectOne("Member.login", members);	
		
	}
	//회원 전체목록
	public List<MemberDTO> findAll() {
		return sql.selectList("Member.findAll");	
	}

	//회원 상세조회
	public MemberDTO findById(long m_number) {
		return sql.selectOne("Member.findById", m_number);	
	}

	// 회원 삭제
	public void delete(long m_number) {
		sql.delete("Member.delete", m_number);	
	}

	//회원 업데이트
	public void update(MemberDTO members) {
		sql.update("Member.update", members);			
	}

	//아이디 중복체크
	public String idDuplicate(String m_id) {
		return sql.selectOne("Member.idDuplicate",m_id);
	}
	// 마이페이지
	public MemberDTO mypage(long m_number) {
		return sql.selectOne("Member.mypage", m_number);	
	}

	

	

}
