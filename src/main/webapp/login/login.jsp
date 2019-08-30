<%@page import="kr.or.ddit.user.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

    <title></title>

    <!-- Bootstrap core CSS -->
    <link href="${cp}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${cp}/css/signin.css" rel="stylesheet">
    
    <!-- jQuery -->
    <script src="${cp}/js/jquery-3.4.1.min.js"></script>
	
	<!-- js-Cookie -->
    <script src="${cp}/js/js.cookie.js"></script>
    
    
    
   <script>
      $(document).ready(function () {
         
         var userId = Cookies.get("userId"); //js-cookie사용
         if(userId != undefined){
            $('#userId').val(userId);
            
            // remember me checkbox 체크
            $('#rememberMe').prop("checked", true);
            $('#pass').focus();
         }
         
         //signin btn 클릭 이벤트 핸들러
         $('#signinBtn').on('click', function () {
	         //로그인 요청
            $('#frm').submit();
            
         }); //signin버튼 클릭
      });
   </script>

  </head>

  <body>

    <div class="container">
       <%
          HttpSession httpSession = request.getSession();
                 User userVo = (User)httpSession.getAttribute("S_USERVO");
                 String userName = "";
                 userName = userVo == null ? "" : userVo.getUserNm();
       %>

      <form id="frm" class="form-signin" action="${cp}/login" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        
        <label for="userId" class="sr-only">userId</label>
        
        <%
           //String userId = (String)request.getAttribute("userId");
           String userId = request.getParameter("userId");
           userId = userId == null ? "" : userId;
        %>
        <input type="text" id="userId" name="userId" class="form-control" 
              placeholder="userId" required autofocus value=<%=userId %>>
              
        <label for="pass" class="sr-only">Password</label>
        
        <input type="password" id="pass" name="pass"
               class="form-control" placeholder="Password" value="brown1234" required> 
               
        <div class="checkbox">
          <label>
            <input id="rememberMe" name="rememberMe" type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button id="signinBtn" class="btn btn-lg btn-primary btn-block" type="button">Sign in</button>
      </form>

    </div> <!-- /container -->
  </body>
</html>