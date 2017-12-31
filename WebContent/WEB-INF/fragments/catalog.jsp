<section id="show">
<table id="books">
	<tr>
		<th>Titre</th>
		<th>Prix</th>
		<th>Auteur(s)</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${books}" var="book">
		<tr>
			<td>${book.title}</td>
			<td>${book.price}</td>
			<td>
				<c:forEach items="${book.authors}" var="author">
					<p>${author.lastName}(${author.nativeCountry})</p>
				</c:forEach>
			</td>
			<td>
				<input type="submit" title="Afficher" value="&#128065;" class="hidden_button_style" form="form-readbook-${book.id}" />
			</td>
		</tr>
		<form method="GET" action="<c:url value='/books/read'/>" id="form-readbook-${book.id}">
			<input type="hidden" value="${book.id}" name="book-id" form="form-readbook-${book.id}" />
		</form>
	</c:forEach>
</table>
</section>