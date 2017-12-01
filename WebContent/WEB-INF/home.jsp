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
			<th>Titre</th>
			<th>Résumé</th>
			<th>Auteur(s)</th>
			<th>Prix</th>
			<th>Disponibilité</th>
		</tr>
		<c:forEach items="${books}" var="book">
			<tr>
				<td>${book.title}</td>
				<td>${book.overview}</td>
				<td>
					<c:forEach items="${book.authors}" var="author">
						<p>${author.lastname} (${author.nativeCountry})</p>
					</c:forEach>
				</td>
				<td>${book.price}</td>
				<td>${book.availability}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>