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
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">${boardNm }</h2>
						<article>

							<div class="container" role="main">

								<h2>board Content</h2>

								<div class="bg-white rounded shadow-sm">

									<div class="board_title">
										<c:out value="${postdt.postTitle}" />
									</div>

									<div class="board_info_box">

										<span class="board_author"><c:out
												value="${postdt.postNo}" />,</span><span class="board_date"><c:out
												value="${postdt.postdate_fmt}" /></span>

									</div>

									<div class="board_content">${postdt.postContent}</div>
									
									<c:choose>
									
										<c:when test="${fn:length(afile) > 0}">
											<div class="board_tag">
												첨부파일 :
												<c:out value="${afile.filename}" />
											</div>
										</c:when>
										
										<c:otherwise>
											<div class="board_tag">
												첨부파일 :
												<c:out value="첨부파일이 없습니다." />
											</div>
										</c:otherwise>
									
									</c:choose>

								</div>



								<div style="margin-top: 20px">

									<button type="button" class="btn btn-sm btn-primary"
										id="btnUpdate">수정</button>

									<button type="button" class="btn btn-sm btn-primary"
										id="btnDelete">삭제</button>

									<button type="button" class="btn btn-sm btn-primary"
										id="btnList">목록</button>

								</div>

							</div>
						</article>


						<a class="btn btn-default pull-right">제품 등록</a>

						<div class="text-center">
							<ul class="pagination">
								<li><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
