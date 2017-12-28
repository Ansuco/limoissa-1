<p class="label">Titre : <c:out value="${book.title}"/></p>
<p class="label">Prix : <c:out value="${book.price}"/></p>
<p class="label">Résumé : <textarea cols="40" rows="6" disabled><c:out value="${book.overview}"/></textarea></p>
<p class="label">Disponibilité : <c:if test="${book.availability == true}">En stock</c:if></p>
<br />
<a href="<c:url value='/books/edit?book-id=${book.id}'/>">Editer</a>
<a href="<c:url value='/books'/>">Retour Catalogue auteur</a>