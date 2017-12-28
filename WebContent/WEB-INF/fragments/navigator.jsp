<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
	pageEncoding="UTF-8"
%>
<c:choose>
	<c:when test="${!empty user}">
		<c:out value="Bonjour ${user.pseudo}"/>&nbsp;&nbsp;
		<a href="<c:url value='/logout'/>">Se déconnecter</a>
		<br />
		<ul>
			<li><a href="<c:url value='/books'/>">Catalogue livre</a></li>
			<li><a href="<c:url value='/authors'/>">Catalogue auteur</a></li>
		</ul>
	</c:when>
	<c:otherwise>
		<a href="<c:url value='/login'/>">Se connecter</a>&nbsp;&nbsp;
		<a href="<c:url value='/signin'/>">S'inscricre</a>
	</c:otherwise>
</c:choose>