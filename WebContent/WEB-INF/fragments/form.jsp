<form action="<c:url value='${param.actionName}'/>" method="POST">
	<c:if test="${param.actionName == 'signin'}">
		<input type="text" name="firstname" placeholder="Prénom" max="90" required />
		<br />
		<input type="text" name="lastname" placeholder="Nom" max="90" required />
		<br />
		<input type="text" name="email" placeholder="Email" max="90" required />
		<br />
	</c:if>
	<input type="text" name="pseudo" placeholder="Pseudo" max="70" required />
	<br />
	<input type="password" name="password" placeholder="Mot de passe" max="40" required />
	<br />
	<br />
	<input type="submit" value="Envoyer" />
</form>

<a href="<c:url value='home'/>">Retour à l'accueil</a>