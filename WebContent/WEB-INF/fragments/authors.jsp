<a href="<c:url value='/authors/add'/>">Ajouter auteur</a>
<table>
	<tr>
		<th>Prénom</th>
		<th>Nom</th>
		<th>Pays</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${authors}" var="author">
		<tr>
			<td>${author.firstName}</td>
			<td>${author.lastName}</td>
			<td>${author.nativeCountry}</td>
			<td>
				<input type="submit" value="&#9998;" class="hidden_button_style" form="form-editauthor-${author.id}" /><input type="submit" value="&#10060;" class="hidden_button_style" form="form-deleteauthor-${author.id}" />
			</td>
		</tr>
		<form method="GET" action="<c:url value='/authors/edit'/>" id="form-editauthor-${author.id}">
			<input type="hidden" value="${author.id}" name="author-id" form="form-editauthor-${author.id}" />
		</form>
		<form method="POST" action="<c:url value='/authors/delete'/>" id="form-deleteauthor-${author.id}">	
			<input type="hidden" value="${author.id}" name="author-id" form="form-deleteauthor-${author.id}" />
		</form>
	</c:forEach>
</table>
<a href="<c:url value='/books'/>">Retour Catalogue livre</a>