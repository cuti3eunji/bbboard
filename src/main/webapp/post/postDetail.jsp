<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 


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

<title>Jsp-basicLib</title>
<%@ include file="/commonJsp/basicLib.jsp"%>
</head>

<body>
	
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
					<div class="col-sm-12 blog-main">
						<h2 class="sub-header">${boardNm }</h2>
							<div class="container" role="main">
								<div class="bg-white rounded shadow-sm">

									<div class="board_title">
										<c:out value="${postdt.postTitle}" />
									</div>

									<div class="board_info_box">
										
										<span class="board_date">
											<c:out value="${postdt.postdate_fmt}" />
										</span>
										
										<span class="board_author">
											<c:out value="${postdt.userId}" />
										</span>
									</div>
									
									<div class="board_content">${postdt.postContent}</div>
									<c:choose>
										<c:when test="${empty afile }">
											<div class="board_tag">
												<c:out value="첨부파일이 없습니다." />
												<div class="text-right">
													<a href="${cp }/updatePost?postNo=${postdt.postNo}" class="btn btn-primary btn-sm">수정</a>
													<a href="${cp }/deletePost?postNo=${postdt.postNo}" class="btn btn-primary btn-sm">삭제</a>
													<a href="${cp }/insertPost" class="btn btn-primary btn-sm">답글</a>
												</div>
											</div>
										</c:when>

										<c:otherwise>
											<div class="board_tag">
												첨부파일 :
												<c:forEach items="${afile }" var="afile">
													<c:out value="${afile.filename}" />
												</c:forEach>
												<div class="text-right">
													<a href="${cp }/updatePost" class="btn btn-primary btn-sm">수정</a>
													<a href="${cp }/deletePost" class="btn btn-primary btn-sm">삭제</a>
													<a href="${cp }/insertPost" class="btn btn-primary btn-sm">답글</a>
												</div>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>

						<!-- 댓글 창 -->
						<hr>
						<form class="form-inline" action="${cp }/insertCmt">
							<div class="input-group col-sm-8">
								<input id="msg" type="text" class="form-control" name="msg" placeholder="댓글을 남겨보세요">
							</div>
								<button type="submit" class="btn btn-default">
									<icon class="glyphicon glyphicon-pencil">
								</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
