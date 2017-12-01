<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>" />
<title>LIMOISSA</title>
</head>
<body>
	<table>
		<tr>
			<th>Nom</th>
			<th>Prénom</th>
			<th>Pays d'origine</th>
		</tr>
		<c:forEach items="${authors}" var="author">
			<tr>
				<td>${author.firstName}</td>
				<td>${author.lastName}</td>
				<td>${author.nativeCountry}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>