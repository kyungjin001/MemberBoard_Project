<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
		input{
	display:block;
	}
</style>

</head>
<body>
	<h2>로그인 페이지</h2>
	<form action= "/member/login" method="post">
			아이디 : <input type ="text" name="m_id" placeholder="아이디를 입력하세요"> <br>
			비밀번호 : <input type ="password" name="m_password" placeholder="비밀번호를 입력하세요"> <br>
			<input type ="submit" value="로그인">
			
	</form>


		
</body>
</html>