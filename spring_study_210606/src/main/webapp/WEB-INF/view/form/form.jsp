<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>로그인 폼</title>
</head>
<body>
	<h2>로그인</h2>
	<form>
		<p>
			<label for="loginType">로그인 타입(form:select), 번호 : ${type}</label>
			<select>
				<option value="">--- 선택하세요 ---</option>
				<c:forEach var="loginType" items="${loginTypes}">
					<option value="${loginType}">${loginType}</option>
				</c:forEach>
			</select>
		</p>
	</form>
</body>
</html>