<form action="<c:url value='${param.actionName}'/>" method="POST">
	<c:if test="${param.actionName == 'signin'}">
		<input type="text" name="firstname" placeholder="firstname" />
		<br />
		<input type="text" name="lastname" placeholder="lastname" />
		<br />
	</c:if>
	<input type="text" name="pseudo" placeholder="pseudo" /> <br /> <input
		type="password" name="password" placeholder="password" /> <br />
	<br /> <input type="submit" value="Go !" />
</form>

<a href="<c:url value='home'/>">Retour à l'accueil</a>