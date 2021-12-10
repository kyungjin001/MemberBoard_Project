<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link href="/resources/css/text.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<!--ajax 용도  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	
</script>

<style>
div {
	width: 800px;
	height: 100px;
}

button {
	margin: 0 auto;
	margin-right: 400px;
}

img {
	width: 150px;
	height: 100px;
}

.a1 {
	width: 150px;
	height: 50px;
}
</style>



</head>
<body>
	<h2>글 상세조회 페이지</h2>


	<table class="table table-dark">
		<tr>
			<th>글번호</th>
			<th>작성자</th>
			<th>글비밀번호</th>
			<th>제목</th>
			<th>내용</th>
			<th>조회수</th>
			<th>작성시간</th>
			<th>파일</th>
			<c:choose>
				<c:when test="${sessionScope.loginId eq board.b_writer}">
					<th>삭제</th>
					<th>수정</th>
				</c:when>
				<c:when test="${sessionScope.loginId eq 'admin'}">
					<th>삭제</th>
				</c:when>
			</c:choose>
			<th>전체목록</th>
			<th>이전목록</th>




		</tr>
		<tr>
			<td>${board.b_number}</td>
			<td>${board.b_writer}</td>
			<td>${board.b_password}</td>
			<td>${board.b_title}</td>
			<td>${board.b_contents}</td>
			<td>${board.b_hits}</td>
			<td>${board.b_date}</td>
			<td><img alt="" src="/resources/upload/${board.b_filename}"></td>




			<c:choose>

				<c:when test="${sessionScope.loginId eq board.b_writer}">
					<td><a href="/board/delete?b_number=${board.b_number}">삭제</a></td>
					<td><a
						href="/board/update?b_number=${board.b_number}&page=${page}">수정</a></td>
				</c:when>

				<c:when test="${sessionScope.loginId eq 'admin'}">
					<td><a href="/board/delete?b_number=${board.b_number}">삭제</a></td>
				</c:when>

			</c:choose>


			<td><a href="/board/findAll">전체목록</a></td>
			<td><a href="/board/paging?page=${page}">이전목록</a></td>



		</tr>


	</table>



	<!-- 댓글작성  -->
	<div id="comment-write" class="d-flex">
		<input type="text" id="c_writer" value="${sessionScope.loginId}"
			class="a1 form-control shadow-sm"> <input type="text"
			id="c_contents" placeholder="댓글내용" class="a1 form-control shadow-sm">
		<button id="comment-write-btn" class="a1 btn btn-light">댓글등록</button>
		&nbsp;
	</div>


	<!-- 댓글목록출력 -->
	<div id="comment-list">
		<table class="table">
			<tr>
				<th>댓글번호</th>
				<th>작성자</th>
				<th>내용</th>
				<th>작성시간</th>
			</tr>


			<c:forEach items="${commentList}" var="comment">
				<tr>
					<td>${comment.c_number}</td>
					<td>${comment.c_writer}</td>
					<td>${comment.c_contents}</td>
					<td>${comment.c_date}</td>
					<c:choose>
						<c:when test="${sessionScope.loginId eq comment.c_writer}">
							<td><a class="btn btn-light"
								href="/comment/delete?b_number=${board.b_number}&page=${page}&c_number=${comment.c_number}">삭제</a></td>
						</c:when>
						<c:when test="${sessionScope.loginId eq 'admin'}">
							<td><a class="btn btn-light"
								href="/comment/delete?b_number=${board.b_number}&page=${page}&c_number=${comment.c_number}">삭제</a></td>
						</c:when>
					</c:choose>
				</tr>
			</c:forEach>
		</table>

	</div>





</body>


<script>
	$("#comment-write-btn").click(function() {
		console.log("댓글등록버튼 클릭");
		const commentWriter = $("#c_writer").val();
		const commentContents = $("#c_contents").val();
		const boardNumber = '${board.b_number}';
		console.log(commentWriter, commentContents, boardNumber);
		$.ajax({
			type : 'post', // 전송방식(get,post,delete,put 등)
			url : '/comment/save', // 요청주소(컨트롤러로 요청하는 주소)
			data : {
				'c_writer' : commentWriter,
				'c_contents' : commentContents,
				'b_number' : boardNumber
			}, // 전송할 데이터
			dataType : 'json',
			success : function(result) {
				console.log(result);
				let output = "<table class='table'>";
				output += "<tr><th>댓글번호</th>";
				output += "<th>작성자</th>";
				output += "<th>내용</th>";
				output += "<th>작성시간</th></tr>";
				for ( let i in result) {
					output += "<tr>";
					output += "<td>" + result[i].c_number + "</td>";
					output += "<td>" + result[i].c_writer + "</td>";
					output += "<td>" + result[i].c_contents + "</td>";
					output += "<td>" + result[i].c_date + "</td>";
					output += "</tr>";
				}
				output += "</table>";
				// id가 comment-list인 div에 출력
				document.getElementById('comment-list').innerHTML = output;
				// 댓글 입력창을 비움. 
				document.getElementById('c_contents').value = '';

			},
			error : function() {
				console.log("흐름을 쓰윽 따라가보세요");
			}

		})
	});
</script>



</html>