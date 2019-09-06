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

<script src="${cp }/SE2/js/HuskyEZCreator.js"></script>
<script type="text/javascript">
var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.

$(document).ready(function() {
	// Editor Setting
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors, // 전역변수 명과 동일해야 함.
		elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
		sSkinURI : "${cp }/SE2/SmartEditor2Skin.html", // Editor HTML
		fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
		htParams : {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseToolbar : true,
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true, 
		}
	});

	// 전송버튼 클릭이벤트
	$("#savebutton").click(function(){
		if(confirm("저장하시겠습니까?")) {
			// id가 smarteditor인 textarea에 에디터에서 대입
			oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);

			// 이부분에 에디터 validation 검증
			if(validation()) {
				$("#frm").submit();
			}
		}
	})
	
	//파일 갯수 제한
	$("#picture").change(function(){
		console.log("length: " + this.files.length);
		if(this.files.length > (5-$(".removefile").length)){
			alert("파일은 최대 5개까지 업로드 가능합니다.");
	         $(this).val("");
		}
	})
	
	
});

// 필수값 Check
function validation(){
	var contents = $.trim(oEditors[0].getContents());
	if(contents === '<p>&nbsp;</p>' || contents === ''){ // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
		alert("내용을 입력하세요.");
		oEditors.getById['smarteditor'].exec('FOCUS');
		return false;
	}

	return true;
}

</script>

<style>
	a.removefile{
		text-decoration : none;
		color : black;
	}
</style>
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
						<h2 class="sub-header">${boardNm } | 수정</h2>
						
						<div class="container" role="main">
							<form action="${cp }/updatePost" method="post" id="frm" enctype="multipart/form-data">
								<div class="form-group col-sm-10">
									<div class="col-sm-10">
										<input type="hidden" name="postNo" value="${postdt.postNo }">
										<input type="hidden" name="boardNo" value="${postdt.boardNo }">
	  									<input type="text" class="form-control" id="usr" name="postTitle" value="${postdt.postTitle }">
	  								</div>
								</div>
								<textarea name="smarteditor" id="smarteditor" rows="10" cols="100" style="width:766px; height:412px;">${postdt.postContent }</textarea> 
								<div class="form-inline">
									
									
									<label>첨부파일 : </label>
									
									<ul class="list-unstyled">
										<c:forEach items="${afList }" var="afile">
											<li>
												<a href="${cp }/delfile?fileNo=${afile.fileNo}" class="glyphicon glyphicon-remove removefile"></a>
												${afile.filename }
											</li>
										</c:forEach>
									</ul>
									
									<input type="file" class="form-control" id="picture" name="picture" multiple >
								</div>
								
								<div class="text-right">
									<input type="submit" class="btn" id="savebutton" value="작성" />
								</div>
							</form>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
