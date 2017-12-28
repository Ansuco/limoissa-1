<form method="POST" action="<c:url value='/books/edit'/>" id="form-editbook">
	<input type="hidden" value="${book.id}" name="book-id" form="form-editbook" />
	<input type="text" value="${book.title}" name="book-title" form="form-editbook" /><br />
	<input type="number" step="0.01" value="${book.price}" name="book-price" form="form-editbook" /><br />
	<textarea name="book-overview" form="form-editbook" cols="40" rows="6">${book.overview}</textarea><br />
	
	<input type="checkbox" <c:if test="${book.availability == true}">checked</c:if> name="book-availability" id="book-availability" form="form-editbook" /><label for="book-availability">En stock</label><br />
	<input type="submit" value="Modifier" form="form-editbook" />
</form>
Auteur(s) : 
	<c:forEach items="${book.authors}" var="author" varStatus="loop">
		${author.firstName.trim()} ${author.lastName.trim()}<input type="submit" value="&#10060;" class="hidden_button_style" form="form-deletjoineauthor-${author.id}" /><c:if test="${!loop.last}">,</c:if>
		<form method="POST" action="<c:url value='/books/deletejoin'/>" id="form-deletjoineauthor-${author.id}">	
			<input type="hidden" value="${author.id}" name="author-id" form="form-deletjoineauthor-${author.id}" />
			<input type="hidden" value="${book.id}" name="book-id" form="form-deletjoineauthor-${author.id}" />
		</form>
	</c:forEach>

<form method="POST" action="<c:url value='/books/addauthors'/>" id="form-book-add-authors">
	<select name="book-add-authors" multiple form="form-book-add-authors">
		<c:forEach items="${authors}" var="author" >
			<option value="${author.id}#${book.id}">${author.firstName} ${author.lastName}</option>
		</c:forEach>
	</select>
	<input type="submit" value="Ajouter auteur(s) existant(s)" form="form-book-add-authors" />	
</form>

<a href="<c:url value='/books/read?book-id=${book.id}'/>">Retour</a>