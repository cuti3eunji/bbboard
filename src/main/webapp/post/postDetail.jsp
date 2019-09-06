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

<style>
	.disabled{
		color : lightgray;
		cursor: not-allowed;
	}
</style>

<script>
	$(function(){
		$('.removeCmt').click(function(){
			$('.removefrm').submit();
		});
	})
</script>
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
													<c:if test="${S_USERVO.userId == postdt.userId }">
														<a href="${cp }/updatePost?postNo=${postdt.postNo}" id="updateBtn" class="btn btn-primary btn-sm">수정</a>
														<a href="${cp }/deletePost?postNo=${postdt.postNo}" id="deleteBtn" class="btn btn-primary btn-sm">삭제</a>
													</c:if>
														<form action="${cp }/writePost" method="post" >
															<input type="hidden" name="parentNo" value="${postdt.postNo }">
															<input type="hidden" name="boardNm" value="${boardNm }">
															<input type="hidden" name="boardNo" value="${boardNo }">
															<button type="submit" class="btn btn-primary btn-sm">답글</button>
														</form>
												</div>
											</div>
										</c:when>

										<c:otherwise>
											<div class="board_tag">
												첨부파일 : 
												<div class="">
													<c:forEach items="${afile }" var="afile">
														<a href="${cp }/fileDownload?fileNo=${afile.fileNo}" download="${afile.filename }">
														<c:out value="${afile.filename}"/><br>
														</a>
													</c:forEach>
												</div>
												<div class="text-right">
													<c:if test="${S_USERVO.userId == postdt.userId }">
														<a href="${cp }/updatePost?postNo=${postdt.postNo}" id="updateBtn" class="btn btn-primary btn-sm">수정</a>
														<a href="${cp }/deletePost?postNo=${postdt.postNo}" id="deleteBtn" class="btn btn-primary btn-sm">삭제</a>
													</c:if>
														<form action="${cp }/writePost" method="post">
															<input type="hidden" name="parentNo" value="${postdt.postNo }">
															<input type="hidden" name="boardNm" value="${boardNm }">
															<input type="hidden" name="boardNo" value="${boardNo }">
															<button type="submit" class="btn btn-primary btn-sm">답글</button>
														</form>
												</div>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>

						<!-- 댓글 창 -->
						<hr>
						<ul class="list-unstyled">
							<c:forEach items="${cmtList }" var="cmt">
								<c:choose>
									<c:when test="${cmt.commentStatus == 0 }">
										<li class="disabled">삭제된 댓글입니다.</li>
									</c:when>
									
									<c:otherwise>
										<li>
									 	<c:if test="${S_USERVO.userId == cmt.userId }">
									 		<a href="${cp }/removeCmt?postNo=${postdt.postNo}&cmtNo=${cmt.commentNo}" class="glyphicon glyphicon-remove removeCmt"></a>
										</c:if>
										 ${cmt.commentContent}&nbsp;&nbsp;&nbsp;[ ${cmt.userId} / ${cmt.commentDate_fmt } ] 
										</li>
									</c:otherwise>
								</c:choose>
							
							</c:forEach>
						</ul>
						<form class="form-inline" action="${cp }/insertCmt" method="get">
							<div class="input-group col-sm-8">
								<input type="hidden" name="postNo" value=${postdt.postNo }>
								<input id="msg" type="text" class="form-control" name="commentContent" placeholder="댓글을 남겨보세요" maxlength="500">
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
