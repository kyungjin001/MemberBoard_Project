<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style type="text/css">
	 *{
        font-family: 'Jua', sans-serif;
    }
    
       h2{
        text-align: center;
    }
    #contant {
        width: 1300px;
        height: 1300px;
        margin: 0 auto;
        /* background-color: blueviolet; */
    }
    #main2 {
        width: 900px;
        height: 800px;
        margin: 0 auto;
        margin-top: 30px;
        border: solid #cccccc 1px;
        /* background-color: aquamarine; */
    }
	 #q1 {
        margin-left: 400px;
        margin-top: 310px;
        width: 150px;
        height: 100px;

    }
 	form {
    margin-top: 100px;
    margin-left: 80px;
    width: 800px;
    height: 500px;
    font-size: 20px;
	 }

 	.a1 {
    width: 650px !important;
    /* background-color: cadetblue; */
    /* margin-right: 200px; */
   	 display: inline-block !important;
    margin-left: 3px;

 	} 
 .a2 {
    width: 200px !important;
    /* background-color: cadetblue; */
    /* margin-right: 200px; */
    display: inline-block !important;
    margin-left: 3px;
    color: gray;

 	}
 	.a3 {
    width: 180px !important;
    /* background-color: cadetblue; */
    /* margin-right: 200px; */
    display: inline-block !important;
    margin-left: 3px;
	 }
	 #e1{
     width: 400px !important;
     display: inline-block !important;
     margin-left:3px;

 	}
 	 #g1{
    width: 750px !important;
     display: inline-block !important;
     margin-left:3px;
     margin-top : 50px;
	 } 
	</style> 


	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	
	
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script>
	
	/* 아이디 입력을 하는 동안에 idDuplicate() 함수를 호출하고 입력된 값을 콘솔에 출력  */
	

function idDuplicate(){
		const id=document.getElementById("m_id").value;
		console.log(id);
		const checkResult=document.getElementById("id-dup-check");
		
		$.ajax({
			type:'post', // 전송방식(get,post,delete, put)
			url: 'idDuplicate', // 요청주소(컨트롤러를 요청하는 주소)
			data : {'m_id':id}, //전송할 데이터
			dataType : 'text', // 요청 후 리턴받을 때의 데이터 형식
			success : function(result){ //요청이 성공적으로 처리됐을때 실행할 함수
				console.log('ajax성공')
				console.log(result)
				
				if(result=="ok"){
					checkResult.style.color = 'green';
					checkResult.innerHTML='멋진아이디네요!!';
				}
				else{
					checkResult.style.color = 'red';
					checkResult.innerHTML='이미 사용중인 아이디입니다.';
				}
			
			},
			error : function(){
				console.log('ajax실패');
			}
		});
		
	}
	
	</script>
	
	
	
	
</head>
<body>
		<section>
		<div id="contant">
		<div id="main2">
		<form action="/member/insert" method="post" enctype="multipart/form-data" >
		<h2>회원가입 페이지</h2>
		 	<br><br>아이디 : <input type ="text" name="m_id" placeholder="아이디를 입력하세요" class="a1 form-control shadow-sm" id="m_id" onblur="idDuplicate()">
		 	<br><span id="id-dup-check"></span>
			<br><br>비밀번호 : <input type ="password" name="m_password" placeholder="비밀번호를 입력하세요" class="a1 form-control shadow-sm">
			<br><br>이름 : <input type ="text" name="m_name" placeholder="이름을 입력하세요" class="a1 form-control shadow-sm">
			<br><br>이메일 : <input type ="text" name="m_email" id="e1" placeholder="이메일을 입력하세요" class="a1 form-control shadow-sm">
			<br><br>전화번호 : <input type ="text" name="m_phone" placeholder="번호를 입력하세요" class="a1 form-control shadow-sm">
			<br><br>프로필사진 : <input type ="file" name="m_file"><br>
			
		<input type ="submit" value="회원가입" class="btn btn-primary" id="g1">
		
		
		</form>
	</div>
	</div>
	</section>


</body>
</html>