<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블로그</title>
</head>
<body>
<h1>로그인 페이지</h1>

<hr/>
<form action="/login" method="POST">
<input type ="text" placeholder="Username" name = "username"/>
<input type = "password" placeholder="Password" name ="password"/>
<button>로그인</button>

</form>
아직 회원가입이 안되셨나요 <a href="/joinForm">회원가입</a>
<a href="/oauth2/authorization/google">구글로그인</a>
<a href="/oauth2/authorization/facebook">Facebook로그인</a>
<a href="/oauth2/authorization/naver">Naver로그인</a>
<a href="/oauth2/authorization/kakao">Kakao로그인</a>
</body>
</html>