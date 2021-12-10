<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
		<h2>save.jsp</h2>


		<form action="/board/save" method="post" enctype="multipart/form-data" >
		 	작성자 : <input type ="text" name="b_writer" value="${sessionScope.loginId}"><br>
			글비밀번호 : <input type ="password" name="b_password"><br>
			제목 : <input type ="text" name="b_title"><br><br>			
			내용 : <textarea name="b_contents" rows="5"></textarea><br>
			파일 : <input type ="file" name="b_file"><br>				
			<input type ="submit" value="작성">
		</form>





</body>
</html>