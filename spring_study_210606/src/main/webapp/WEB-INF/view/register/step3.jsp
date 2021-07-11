<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원가입</title>
</head>
<body>
	<p><strong>${registerRequest.name}님</strong>
		회원 가입(회원번호:${memberNum})을 완료했습니다.</p>
	<p>
<%-- 		<a href="<c:url value='main' />"> --%>
		<a href="/spring_study_210606/main">
			[첫 화면 이동]
		</a>
	</p>
</body>
</html>