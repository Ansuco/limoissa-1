<form method="POST" action="<c:url value='/books/edit'/>" id="form-editbook">
	<input type="hidden" value="${book.id}" name="book-id" form="form-editbook" />
	<input type="text" value="${book.title}" name="book-title" form="form-editbook" /><br />
	<input type="number" step="0.01" value="${book.price}" name="book-price" form="form-editbook" /><br />
	<textarea name="book-overview" form="form-editbook" cols="40" rows="6">${book.overview}</textarea><br />
	
	<input type="checkbox" <c:if test="${book.availability == true}">checked</c:if> name="book-availability" id="book-availability" form="form-editbook" /><label for="book-availability">En stock</label><br />
	<input type="submit" value="Modifier" form="form-editbook" />
</form>
<a href="<c:url value='/books/read?book-id=${book.id}'/>">Retour</a>