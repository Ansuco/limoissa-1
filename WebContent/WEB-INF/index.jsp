<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
	pageEncoding="UTF-8"
	errorPage = "/error.jsp"
%>
<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="<c:url value='/resources/css/styles.css'/>" />
		<title>${title}</title>
	</head>
<body>
	<!-- Header -->
	<jsp:include page="fragments/header.jsp" >
		<jsp:param value="${user}" name="user" />
	</jsp:include>
	<!-- Body -->
	<jsp:include page="fragments/body.jsp" >
		<jsp:param value="${user}" name="user" />
		<jsp:param value="${actionName}" name="user" />
	</jsp:include>
	<!-- Footer -->
	<jsp:include page="fragments/footer.jsp" />
</body>
</html>