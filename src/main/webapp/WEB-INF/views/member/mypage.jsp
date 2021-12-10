<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">


<script>
	function logout() {
		location.href = "logout";
	}
</script>
<style>
img {
	width: 150px;
	height: 100px;
}


</style>
</head>
<body>

	<div id="q1">
		<p>${sessionScope.loginId} 님 환영합니다. </p>
	<!-- 로그아웃 버튼을 클릭하면 logout 이라는 주소요청 -->
	<button onclick="logout()">로그아웃</button>
	<button><a href = "/">홈으로 가기</a></button>
	</div>

	

	<table class="table table-dark">
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
			<c:if test="${sessionScope.loginId eq 'admin'}">
			<th>회원전체목록</th>
			</c:if>


		</tr>
		<tr>

			<td>${mypage.m_number}</td>
			<td>${mypage.m_id}</td>
			<td>${mypage.m_password}</td>
			<td>${mypage.m_name}</td>
			<td>${mypage.m_email}</td>
			<td>${mypage.m_phone}</td>
			<td><img alt="" src="/resources/upload/${mypage.m_filename}"></td>



			<td><a href="/member/delete?m_number=${mypage.m_number}">삭제</a></td>
			<td><a href="/member/update?m_number=${mypage.m_number}">수정</a></td>
			<c:if test="${sessionScope.loginId eq 'admin'}">
			<td><a href="/member/findAll">전체목록</a></td>
			</c:if>




		</tr>


	</table>



</body>
</html>