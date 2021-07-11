<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>설문조사</title>
</head>
<body>
	<h2>설문조사</h2>
	<form method="post">
		<c:forEach var="q" items="${questions}" varStatus="status">
			<p>
				${status.index + 1}. ${q.title}<br>
				<!-- 설문조사에 선택이 되어 있을 때 -->
				<c:if test="${q.choice}">
					<c:forEach var="option" items="${q.options}">
						<label>
							<input type="radio" name="responses[${status.index}]" value="${option}">
							${option}
						</label>
					</c:forEach>					
				</c:if>
				<!-- 설문조사에 선택이 없을 때 -->
				<c:if test="${!q.choice}">
					<input type="text" name="responses[${status.index}]">
				</c:if>
		</c:forEach>
		
		<br><br><br>
		<p>
			<label>응답자 위치:<br>
				<input type="text" name="res.location">
			</label>
		</p>
		<p>
			<label>응답자 나이:<br>
				<input type="text" name="res.age">
			</label>
		</p>
		
		<input type="submit" value="전송">
	</form>
</body>
</html>