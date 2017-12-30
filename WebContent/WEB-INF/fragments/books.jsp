<a href="<c:url value='/books/add'/>">Ajouter livre</a>
<section>
<table id="books">
	<tr>
		<th>Auteur(s)</th>
		<th>Titre</th>
		<th>Prix</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${books}" var="book">
		<tr>
			<td>
				<c:forEach items="${book.authors}" var="author">
					<p>${author.lastName}(${author.nativeCountry})</p>
				</c:forEach>
			</td>
			<td>${book.title}</td>
			<td>${book.price}</td>
			<td>
				<input type="submit" value="&#128065;" class="hidden_button_style" form="form-readbook-${book.id}" /><input type="submit" value="&#9998;" class="hidden_button_style" form="form-editbook-${book.id}" /><input type="submit" value="&#10060;" class="hidden_button_style"form="form-deletebook-${book.id}" />
			</td>
		</tr>
		<form method="GET" action="<c:url value='/books/read'/>" id="form-readbook-${book.id}">
			<input type="hidden" value="${book.id}" name="book-id" form="form-readbook-${book.id}" />
		</form>
		<form method="GET" action="<c:url value='/books/edit'/>" id="form-editbook-${book.id}">
			<input type="hidden" value="${book.id}" name="book-id" form="form-editbook-${book.id}" />
		</form>
		<form method="POST" action="<c:url value='/books/delete'/>" id="form-deletebook-${book.id}">	
			<input type="hidden" value="${book.id}" name="book-id" form="form-deletebook-${book.id}" />
		</form>
	</c:forEach>
</table>
</section>