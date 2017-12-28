<form method="POST" action="<c:url value='/authors/edit'/>" id="form-editauthor">
	<input type="hidden" value="${author.id}" name="author-id" form="form-editauthor" />
	<input type="text" placeholder="Prénom" value="${author.firstName}" name="author-firstName" form="form-editauthor" required  max="70" /><br />
	<input type="text" placeholder="Nom" value="${author.lastName}" name="author-lastName" form="form-editauthor" required  max="70" /><br />
	<select name="author-nativeCountry" form="form-editauthor" >
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
	<input type="submit" value="Modifier" form="form-editauthor" />
</form>
<a href="<c:url value='/authors/read?author-id=${author.id}'/>">Retour</a>