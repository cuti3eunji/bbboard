<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="nav nav-sidebar">
	<li class="active"><a href="${cp}/managementBoard">게시판 관리<span class="sr-only">(current)</span></a></li>
	
	<c:forEach items="${stBoardList}" var="board">
		<li class="active"><a href="${cp }/postList?boardNo=${board.boardNo}&boardNm=${board.boardNm}">${board.boardNm }<span class="sr-only">(current)</span></a></li>
	</c:forEach>
</ul>