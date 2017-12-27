<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
	pageEncoding="UTF-8"
	errorPage = "/error.jsp"
	%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>" />
<title>${title}</title>
</head>
<body>
	<c:choose>
		<c:when test="${!empty user}">
            <a href="<c:url value='/logout'/>">Se déconnecter</a>
            <c:choose>
            	<c:when test = "${actionName == 'books'}">
            		<jsp:include page="fragments/books.jsp" />
         		</c:when>
         		<c:when test = "${actionName == 'books:edit'}">
           			<jsp:include page="fragments/editBook.jsp" />
         		</c:when>
         		<c:when test = "${actionName == 'books:add'}">
          			<jsp:include page="fragments/addBook.jsp" />
         		</c:when>
         		<c:when test = "${actionName == 'authors'}">
            		<jsp:include page="fragments/authors.jsp" />
         		</c:when>
         		<c:when test = "${actionName == 'authors:edit'}">
            		<jsp:include page="fragments/editAuthor.jsp" />
         		</c:when>
            </c:choose>
         </c:when>
		<c:when test="${actionName == 'signin' || actionName == 'login'}">
			<jsp:include page="fragments/userForm.jsp">
				<jsp:param value="${actionName}" name="actionName" />
			</jsp:include>
		</c:when>
         <c:otherwise>
			<a href="<c:url value='/login'/>">Se connecter</a>
			<a href="<c:url value='/signin'/>">S'inscricre</a>
         </c:otherwise>
      </c:choose>
</body>
</html>