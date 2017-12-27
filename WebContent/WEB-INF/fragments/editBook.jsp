<form method="POST" action="<c:url value='/books/edit'/>" id="form-editbook">
	<input type="hidden" value="${book.id}" name="book-id" form="form-editbook" />
	<input type="text" value="${book.title}" name="book-title" form="form-editbook" /><br />
	<input type="text" value="${book.price}" name="book-price" form="form-editbook" /><br />
	<input type="text" value="${book.overview}" name="book-overview" form="form-editbook" /><br />
	<input type="checkbox" <c:if test="${book.availability == true}">checked</c:if> name="book-availability" id="book-availability" form="form-editbook" /><label for="book-availability">disponibilit&eacute;</label><br />
	<input type="submit" value="Modifier" form="form-editbook" />
</form>
<a href="<c:url value='/books'/>">Retour Catalogue livre</a>