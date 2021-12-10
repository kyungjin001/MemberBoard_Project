<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!--JSTL(JSP Standard Tag Library)사용  -->
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<style>

img{

width:150px;
height:100px;
}


 	button{
	margin-left : 400px;
	
	} 


</style>



</head>
<body>
	<h2>글목록</h2>
	
	<form action="/board/search" method="get">
	<select name="searchtype">
		<option value="b_title">제목</option>
		<option value="b_writer">작성자</option>
	</select>
	<input type="text" name="keyword" placeholder="검색어..">	
	<input type="submit" value="검색">	
	</form>
	
	
	
		<button><a href="/member/mypage?m_number=${loginNumber}">MyPage</a></button>		
		
		<button><a href = "/board/save">글쓰기화면</a></button>
	
			<c:if test="${sessionScope.loginId eq 'admin'}">
		<button><a href="/member/findAll">회원목록(관리자만 보여요)</a></button>
		</c:if>
		
				<c:if test="${sessionScope.loginId eq 'admin'}">
		<button><a href="/board/findAll">글목록(관리자만 보여요)</a></button>
		</c:if>
		
		<button><a href = "/">홈으로 가기</a></button>
		
	
	
	<table class = "table table-dark">
	<tr>
	<th>글번호</th>
	<th>작성자</th>
	<th>글비밀번호</th>
	<th>제목</th>
	<th>내용</th>
	<th>조회수</th>
	<th>작성시간</th>
	<th>첨부파일명</th>
	<c:if test="${sessionScope.loginId eq 'admin'}">
	<th>삭제(관리자용)</th>
	</c:if>		
		
	
	

	

	</tr>
	<c:forEach items="${bList}" var="b">
		<tr>
		<td>${b.b_number}</td>
		<td>${b.b_writer}</td>
		<td>${b.b_password}</td>
 		<td><a href="/board/detail?b_number=${b.b_number}&page=${paging.page}">${b.b_title}</a></td>
 		<td>${b.b_contents}</td>
		<td>${b.b_hits}</td>
		<td>${b.b_date}</td>
		<td><img alt="" src="/resources/upload/${b.b_filename}"></td>
		<c:if test="${sessionScope.loginId eq 'admin'}">
        <td><a href="/board/delete?b_number=${b.b_number}">삭제</a></td> 
        
		</c:if>		
		
		
		
		</tr>
	
	
		</c:forEach>
	</table>
	
		<div>
		<c:choose>
			<c:when test="${paging.page<=1}">
				[이전]&nbsp;
			</c:when>
			<c:otherwise>
			<%-- 현재 페이지에서 1감소한 페이지 요청하는 링크 --%>
				<a href="/board/paging?page=${paging.page-1}">[이전]</a>&nbsp;
			</c:otherwise>
		</c:choose>
		<%-- for(int i=startPage; i<endPage; i++) --%>
		<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
			<c:choose>
			<%-- i값이 현재 페이지(page) 값과 같다면 i출력 --%>
				<c:when test="${i eq paging.page}">
					${i}
				</c:when>
				<c:otherwise>
					<a href="/board/paging?page=${i}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	
		<c:choose>
			<c:when test="${paging.page>=paging.maxPage}">
				[다음]
			</c:when>
			<c:otherwise>
				<a href="/board/paging?page=${paging.page+1}">[다음]</a>
			</c:otherwise>
		</c:choose>
	</div>

	
	
	
	
	
	
</body>
</html>