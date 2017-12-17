<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>" />
<title>LIMOISSA</title>
</head>
<body>
	<c:if test="${actionName != 'home'}">
		<c:import url="fragments/books.jsp" />
	</c:if>
</body>
</html>