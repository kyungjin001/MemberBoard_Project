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
	<script>
	/* 비밀번호 입력창에서 비밀번호를 입력받고 DB에서 가져온 비밀번호와 비교해서 일치하면 update처리 일치하지 않으면 비밀번호가 일치하지 않습니다. alert 출력 */
	function update(){
		
	 	console.log('update 함수호출')		
		// 이 문장이 아래 form을 전송하는 문장
        const pw = document.querySelector('#b_password').value;
        const pw1 = document.getElementById('b_password').value;		
        const pwDB = '${board.b_password}';
	 	console.log(pwDB);
	 	if(pw==pwDB){
		update_form.submit();	 		
	 	}else{
	 		alert('비밀번호가 틀립니다.');
	 	}

	}
	
	</script>
</head>
<body>
	<h2>update.jsp</h2>
	 
	
		<form action="/board/update" method="post" name= "update_form" enctype="multipart/form-data" >
		<h2>글쓰기 페이지</h2>
		<input type ="hidden" name="page" value= ${page}>
		<!-- 글번호로 받기 때문에 무조건 글번호가 있어야함  -->
		 	<input type ="hidden" name="b_number" value="${board.b_number}" readonly> 
		 	작성자 : <input type ="text" name="b_writer" class="a1 form-control shadow-sm" id="b_writer" value="${board.b_writer}">
			글비밀번호 : <input type ="password" name="b_password" class="a1 form-control shadow-sm" id="b_password">
			제목 : <input type ="text" name="b_title" class="a1 form-control shadow-sm" value="${board.b_title}">		
 			내용 : <textarea name="b_contents" rows="5">${board.b_contents}</textarea><br>
 			프로필사진 : <input type ="file" name="b_file"><br>							
			<input type ="button" value="수정" onclick="update()">
		
		
		</form>

</body>
</html>