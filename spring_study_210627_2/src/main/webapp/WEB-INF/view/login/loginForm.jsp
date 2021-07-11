<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title><spring:message code="login.title"/></title>
</head>
<body>
	<c:if test="${empty authInfo}">
		<form:form modelAttribute="loginCommand">
		<p>
			<label><spring:message code="email" /><br>
				<form:input path="email" />
				<form:errors path="email" />
			</label>
		</p>
		<p>
			<label><spring:message code="password" /><br>
				<form:password path="password" />
				<form:errors path="password" />
			</label>
		</p>
		<p>
			<label><spring:message code="rememberEmail" />
				<form:checkbox path="rememberEmail" />
			</label>
		</p>
		<form:errors />
		<br><input type="submit" value="<spring:message code="login.btn" />">
		</form:form>
	</c:if>
	<c:if test="${not empty authInfo}">
		<meta http-equiv="refresh" content="0;url=/spring_study_210627_2/main">
	</c:if>
</body>
</html>