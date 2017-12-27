<form method="POST" action="<c:url value='/authors/add'/>" id="form-addauthor">
	<input type="hidden" value="${author.id}" name="author-id" form="form-addauthor" />
	<input type="text" placeholder="Prénom" value="${author.firstName}" name="author-firstName" form="form-addauthor" required  max="70" /><br />
	<input type="text" placeholder="Nom"  value="${author.lastName}" name="author-lastName" form="form-addauthor" required  max="70" /><br />
	<select name="author-nativeCountry" form="form-addauthor" >
		<c:forEach var="country" items="${countries}">
			<c:if test = "${author.nativeCountry == country}">
        		<option value="${country}" selected>${country.name}</option>
        	</c:if>
        	<c:if test = "${author.nativeCountry != country}">
        		<option value="${country}">${country.name}</option>
        	</c:if>
    	</c:forEach>
	</select>
	<br />
	<input type="submit" value="Ajouter" form="form-addauthor" />
</form>
<a href="<c:url value='/authors'/>">Retour Catalogue auteur</a>