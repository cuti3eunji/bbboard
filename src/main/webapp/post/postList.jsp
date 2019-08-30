<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp-basicLib</title>

<%@ include file="/commonJsp/basicLib.jsp" %>
<style>
	tr:nth-child(odd) {
		background: #FAFAFA;
	}
	
	.postTr:hover {
		background: #FFCA6C;
		cursor: pointer;
	}
	.table th{
		text-align: center;
	}
	
	
</style>
<script>
	$(document).ready(function() {
		
		// 사용자 정보 클릭 시 이벤트 핸들러
		$(".postTr").on("click", function() {
			// 클릭된 tr태그의 data 불러오기
			var data = $(this).data("postno");
			console.log("data: " + data);
			
			// input 태그에 값 설정
			$("#postNo").val(data);
			
			// form 태그 이용하여 전송
			console.log("serialize: " + $("#frm").serialize());
			
			$("#frm").submit();
		});
		
	});
</script>
</head>

<body>
	<!-- 개발자 입장에서 데이터를 전송하기 위하여 사용하는 form -->
	<form id="frm" action="${cp }/postDetail" method="post">
		<input type="hidden" id="postNo" name="postNo"/>
	</form>
	<!-- header -->
	<%@ include file="/commonJsp/header.jsp" %>

	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				<!-- left -->
				<%@ include file="/commonJsp/left.jsp" %>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">${boardNm }ttt</h2>
						<div class="table-responsive">
							<table class="table">
								<tr>
									<th>글 번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>작성일시</th>
								</tr>
								
								<c:forEach items="${postList}" var="post">
									<tr class="postTr" data-postNo="${post.postNo }">
										<td>${post.postNo}</td>
										<td>
											<c:forEach begin="0" end="${(post.level-1)*2 }" var="i">
												&nbsp;
											</c:forEach>
											<c:if test="${(post.level-1)*2 != 0 }">
												┖
											</c:if>
										${post.postTitle}</td>
										<td>${post.userId}</td>
										<td>${post.postdate_fmt}</td>
									</tr>
								</c:forEach>
								
							</table>
						</div>

						<a class="btn btn-default pull-right" id="writePostBtn">새글 등록</a>

						<div class="text-center">
							<ul class="pagination">
								<c:choose>
									<c:when test="${pageVo.page == 1 }">
										<li class="disabled">
											<span aria-hidden="true">&laquo;</span>
										</li>
									</c:when>
									<c:otherwise>
										<li>
											<a href="${cp }/postList?page=${pageVo.page - 1}" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
											</a>
										</li>
									</c:otherwise>
								</c:choose>
								
								<c:forEach begin="1" end="${paginationSize }" var="idx">
									<c:choose>
										<c:when test="${idx == pageVo.page }">
											<li class="active"><span>${idx }</span></li>
											 
										</c:when>
										<c:otherwise>
											<li><a href="${cp }/postList?page=${idx }">${idx }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								
								<c:choose>
									<c:when test="${pageVo.page == paginationSize }">
										<li class="disabled">
											<span aria-hidden="true">&raquo;</span>
										</li>
									</c:when>
									<c:otherwise>
										<li>
											<a href="${cp }/postList?page=${pageVo.page + 1}" aria-label="Previous">
												<span aria-hidden="true">&raquo;</span>
											</a>
										</li>
									</c:otherwise>
								</c:choose>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>