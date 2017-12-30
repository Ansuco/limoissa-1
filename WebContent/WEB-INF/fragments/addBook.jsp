<form method="POST" action="<c:url value='/books/add'/>" id="form-addbook"></form>
<input type="hidden" value="${book.id}" name="author-id" form="form-addbook" />
<input type="text" placeholder="Titre" value="${book.title}" name="book-title" form="form-addbook" required  max="70" /><br />
<input type="number" step="0.01" placeholder="Prix" value="${book.price}" name="book-price" form="form-addbook"  /><br />
<textarea name="book-overview" placeholder="Résumé" cols="40" rows="6" form="form-addbook">${book.overview}</textarea><br />
<input type="checkbox" id="book-availability" name="book-availability" form="form-addbook" <c:if test="${book.availability == true}">checked</c:if> />
<label for="book-availability">En stock</label><br />
Auteur(s) : 
<c:forEach items="${sessionScope.tmpauthorsforbook}" var="author" varStatus="loop">
	${author.firstName} ${author.lastName}<c:if test="${!loop.last}">,</c:if>
</c:forEach><br />
<input type="submit" value="Ajouter" form="form-addbook" />

<p>Liste Auteur :</p>
<form method="POST" action="<c:url value='/books/add?mode=tmpauthors'/>" id="form-book-add-authors">
	<select name="book-add-authors" multiple form="form-book-add-authors">
		<c:forEach items="${authors}" var="author" >
			<option value="${author.id}">${author.firstName} ${author.lastName}</option>
		</c:forEach>
	</select>
	<br />
	<input type="submit" title="Ajouter auteur(s)" value="Ajouter la selection" form="form-book-add-authors" />	
</form>

<a href="<c:url value='/books'/>">Retour Catalogue livre</a>