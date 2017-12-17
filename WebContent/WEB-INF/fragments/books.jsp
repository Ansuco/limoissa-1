
<form method="POST" action="<c:url value='/books/add'/>"
	id="form-addbook"></form>
<table>
	<tr>
		<th>Auteur(s)</th>
		<th>Titre</th>
		<th>Prix</th>
		<th>Action</th>
	</tr>
	<tr>
		<td><input type="text" name="author-firstname"
			form="form-addbook" /></td>
		<td><input type="text" name="book-title" form="form-addbook" /></td>
		<td><input type="text" name="book-price" form="form-addbook" /></td>
		<td><input type="submit" value="Go !" form="form-addbook" /></td>
	</tr>
	<c:forEach items="${books}" var="book">
		<tr>
			<td><c:forEach items="${book.authors}" var="author">
					<p>${author.lastName}(${author.nativeCountry})</p>
				</c:forEach></td>
			<td>${book.title}</td>
			<td>${book.price}</td>
			<td>&#9998; &#10060;</td>
		</tr>
	</c:forEach>
</table>