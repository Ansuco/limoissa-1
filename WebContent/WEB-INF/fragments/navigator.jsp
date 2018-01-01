<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
	pageEncoding="UTF-8"
%>
<c:choose>
	<c:when test="${!empty user}">
		<c:out value="Bonjour ${user.pseudo}"/>&nbsp;&nbsp;
		<a href="<c:url value='/logout'/>">Se déconnecter</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		<c:if test="${user.role eq 'ADMIN'}">
			<ul>
				<li><a href="<c:url value='/books'/>">Catalogue Livre</a></li>
				<li><a href="<c:url value='/authors'/>">Catalogue Auteur</a></li>
				<li><a href="<c:url value='/importbooks'/>">Importer Livres</a></li>
			</ul>
		</c:if>
	</c:when>
	<c:otherwise>
		<a href="<c:url value='/login'/>">Se connecter</a>&nbsp;&nbsp;|&nbsp;
		<a href="<c:url value='/signin'/>">S'inscricre</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</c:otherwise>
</c:choose>