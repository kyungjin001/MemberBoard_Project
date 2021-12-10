package com.icia.board.dto;




import java.sql.Timestamp;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardDTO {

	
	//필드 선언
	private long b_number;
	private String b_writer;
	private String b_password;
	private String b_title;
	private String b_contents;
	private int b_hits;
	private Timestamp b_date;	
	private MultipartFile b_file; // jsp에서 컨트롤러로 넘어올 떄 파일 자체를 담는 필드
	// DB에 파일을 담는 것이 아니라 파일 이름을 담는 것. 파일은 별도의 경로에 저장
	private String b_filename;
	private String m_id;

	

		
	
	
}
