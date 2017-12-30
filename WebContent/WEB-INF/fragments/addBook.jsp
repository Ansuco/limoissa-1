<form method="POST" action="<c:url value='/books/add'/>" id="form-addbook">
	<input type="hidden" value="${book.id}" name="author-id" form="form-addbook" />
	<input type="text" placeholder="Titre" value="${book.title}" name="book-title" form="form-addbook" required  max="70" /><br />
	<input type="number" step="0.01" placeholder="Prix"  value="${book.price}" name="book-price" form="form-addbook"  /><br />
	<textarea name="book-overview" cols="40" rows="6" form="form-addbook">${book.overview}</textarea><br />
	<input type="checkbox" id="book-availability" name="book-availability" form="form-addbook" <c:if test="${book.availability == true}">checked</c:if> />
	<label for="book-availability">En stock</label><br />
	<input type="submit" value="Ajouter" form="form-addbook" />
</form>

Auteur(s) : 
	<c:forEach items="${book.authors}" var="author" varStatus="loop">
		${author.firstName} ${author.lastName}<input type="submit" value="&#10060;" class="hidden_button_style" form="form-deletjoineauthor-${author.id}" /><c:if test="${!loop.last}">,</c:if>
		<form method="POST" action="<c:url value='/books/deletejoin'/>" id="form-deletjoineauthor-${author.id}">	
			<input type="hidden" value="${author.id}" name="author-id" form="form-deletjoineauthor-${author.id}" />
			<input type="hidden" value="${book.id}" name="book-id" form="form-deletjoineauthor-${author.id}" />
		</form>
	</c:forEach>
<p>Liste auteur :</p>
<form method="POST" action="<c:url value='/books/addauthors'/>" id="form-book-add-authors">
	<select name="book-add-authors" multiple form="form-book-add-authors">
		<c:forEach items="${authors}" var="author" >
			<option value="${author.id}#${book.id}">${author.firstName} ${author.lastName}</option>
		</c:forEach>
	</select>
	<input type="submit" value="Ajouter auteur(s) existant(s)" form="form-book-add-authors" />	
</form>

<a href="<c:url value='/books'/>">Retour Catalogue livre</a>