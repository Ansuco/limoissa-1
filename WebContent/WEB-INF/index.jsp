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
         <c:when test = "${actionName == 'books'}">
            <c:import url="fragments/books.jsp" />
         </c:when>
         <c:when test = "${actionName == 'edit'}">
           <c:import url="fragments/editBook.jsp" />
         </c:when>
         <c:when test = "${actionName == 'add'}">
           <c:import url="fragments/addBook.jsp" />
         </c:when>
         <c:otherwise>
            <c:import url="fragments/books.jsp" />
         </c:otherwise>
      </c:choose>
</body>
</html>