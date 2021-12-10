<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">



<style>

img{

width:150px;
height:100px;
}


</style>


</head>
<body>
	<h2>회원상세조회 페이지</h2>
		
	
	

	<table class = "table table-dark">
	<tr>
	<th>회원번호</th>
	<th>아이디</th>
	<th>비밀번호</th>
	<th>이름</th>
	<th>이메일</th>
	<th>전화번호</th>
	<th>프로필사진</th>
	<th>삭제</th>
	<th>수정</th>
	

	</tr>
		<tr>
		<td>${members.m_number}</td>
		<td>${members.m_id}</td>
		<td>${members.m_password}</td>
		<td>${members.m_name}</td>
		<td>${members.m_email}</td>
		<td>${members.m_phone}</td>
		<td><img alt="" src="/resources/upload/${members.m_filename}"></td>
		
		
		
 			<td><a href="/member/delete?m_number=${members.m_number}">삭제</a></td> 
			<td><a href="/member/update?m_number=${members.m_number}">수정</a></td>
			
			
	
			
 		
		</tr>
	
	
	</table>
















</body>
</html>