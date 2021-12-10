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
        const pw = document.getElementById('m_password').value;
	 	console.log(pw);		
        const pwDB = '${members.m_password}';
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
	
	
		<form action="/member/update" method="post" name= "update_form" enctype="multipart/form-data" >
				<input type ="hidden" name="m_number" value="${members.m_number}">		
 				아이디 : <input type ="text" name="m_id" value="${members.m_id}" readonly>
				비밀번호 : <input type ="text" name="m_password" id="m_password">
				이름 : <input type ="text" name="m_name" value="${members.m_name}" readonly>
				이메일 : <input type ="text" name="m_email" value="${members.m_email}">
				전화번호 : <input type ="text" name="m_phone" value="${members.m_phone}">	
				프로필사진 : <input type ="file" name="m_file"><br>
				
			<input type ="button" value="수정" onclick="update()">
		</form>

</body>
</html>