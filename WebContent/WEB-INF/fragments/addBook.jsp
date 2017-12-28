<form method="POST" action="<c:url value='/books/add'/>" id="form-addbook">
	<input type="hidden" value="${book.id}" name="author-id" form="form-addbook" />
	<input type="text" placeholder="Titre" value="${book.title}" name="book-title" form="form-addbook" required  max="70" /><br />
	<input type="number" step="0.01" placeholder="Prix"  value="${book.price}" name="book-price" form="form-addbook"  /><br />
	<textarea name="book-overview" cols="40" rows="6" form="form-addbook">${book.overview}</textarea><br />
	<input type="checkbox" id="book-availability" name="book-availability" form="form-addbook" <c:if test="${book.availability == true}">checked</c:if> />
	<label for="book-availability">En stock</label><br />
	<input type="submit" value="Ajouter" form="form-addbook" />
</form>
<a href="<c:url value='/books'/>">Retour Catalogue livre</a>