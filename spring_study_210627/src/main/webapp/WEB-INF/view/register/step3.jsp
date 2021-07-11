<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title><spring:message code="member.register" /></title>
</head>
<body>
	<p>
		<spring:message code="register.done">
			<spring:argument value="${registerRequest.name}" />
			<spring:argument value="${registerRequest.email}" />  
		</spring:message>
	<p>
		<a href="/spring_study_210627/main">
			[<spring:message code="go.main" />]
		</a>
	</p>
</body>
</html>