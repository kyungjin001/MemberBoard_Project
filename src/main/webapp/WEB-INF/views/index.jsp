<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function logout() {
		location.href = "/member/logout";
	}
</script>
</head>
<body>
		<h2>index.jsp</h2>
		<a href = "/member/insertform">회원가입페이지링크</a>
		<a href = "/member/login">로그인페이지링크</a>
		
<!-- 		<a href = "/board/save">글쓰기화면</a><br>
		<a href = "/board/findAll">글목록출력</a><br> -->

<!-- 		<a href = "/board/paging">페이징</a><br>
 -->		
				<p>${sessionScope.loginId} 님 환영합니다. </p>
				<button onclick="logout()">로그아웃</button>
				
		
		

</body>
</html>