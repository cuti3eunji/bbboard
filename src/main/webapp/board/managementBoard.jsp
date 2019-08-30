<%@page import="kr.or.ddit.user.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp-Main</title>

<%@include file="/commonJsp/basicLib.jsp"%>

<script>
$(document).ready(function () {
	
	//생성버튼 클릭시 이벤트
	$('#insertBtn').on('click', function(){
		$('#insertFrm').submit();
		
	})
	
	$('.updateBtn').on('click', function(){
		$(this).parent().submit();
		
	})
	
});

</script>


</head>

<body>

	<!-- Header -->
	<%@include file="/commonJsp/header.jsp"%>


	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				<!-- left -->
				<%@ include file="/commonJsp/left.jsp"%>

			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


				<div class="blog-header">
					<h2 class="blog-post-title">게시판 관리</h2>
				</div>
				
				<!-- 게시판 생성  -->
				<form class="form-inline" id="insertFrm" action="${cp }/insertBoard" method="post" >
					<div class="form-group">
							<div class="form-group">
								<label for="focusedInput">게시판 이름 </label>
								<input class="form-control" name="boardNm" type="text">
							</div>
							<select class="form-control" name="boardStatus">
								<option value="1" selected>사용</option>
								<option value="0">사용안함</option>
							</select>
							<button type="button" class="btn btn-danger" id="insertBtn">생성</button>
							<br><br>
					</div>
				</form>
				
				<!-- 게시판 관리(수정) -->
				<c:forEach items="${boardList }" var="board">
					<form id="updateFrm${board.boardNo }" action="${cp }/updateBoard" method="post" class="form-inline">
						<div class="form-group">
							<div class="form-group">
								<label for="focusedInput">게시판 이름 </label>
								<input type="hidden" name="boardNo" value="${board.boardNo }">
								<input class="form-control upName" name="boardNm" type="text"
									value="${board.boardNm }">
							</div>
							<select class="form-control" name="boardStatus">
								<c:choose>
									<c:when test="${board.boardStatus == 1 }">
										<option value="1" selected>사용</option>
										<option value="0">사용안함</option>
									</c:when>
									<c:otherwise>
										<option value="1">사용</option>
										<option value="0" selected>사용안함</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
							<button type="button" class="btn btn-primary updateBtn" onclick="updateBtn">수정</button>
							<br><br>
					</form>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>
