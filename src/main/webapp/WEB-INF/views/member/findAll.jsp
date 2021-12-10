<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--JSTL(JSP Standard Tag Library)사용  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>


<style>
img {
	width: 150px;
	height: 100px;
}
</style>



</head>
<body>
	<h2>전체회원목록(관리자용)</h2>
	<table class="table table-dark">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>프로필사진</th>
			<th>상세조회</th>
			<th>삭제</th>
			<th>수정</th>


		</tr>
		<c:forEach items="${mList}" var="m">
			<tr>
				<td>${m.m_number}</td>
				<td>${m.m_id}</td>
				<td>${m.m_password}</td>
				<td>${m.m_name}</td>
				<td>${m.m_email}</td>
				<td>${m.m_phone}</td>
				<td><img alt="" src="/resources/upload/${m.m_filename}"></td>
				<td><a href="detail?m_number=${m.m_number}">조회</a></td>
				<td><a href="delete?m_number=${m.m_number}">삭제</a></td>
				<c:if test="${sessionScope.loginId eq m.m_id}">
				<td><a href="update?m_number=${m.m_number}">수정</a></td>
				</c:if>
			</tr>

		</c:forEach>
	</table>












</body>
</html>