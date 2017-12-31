<p class="label">Prénom : <c:out value="${author.firstName}"/></p>
<p class="label">Nom : <c:out value="${author.lastName}"/></p>
<p class="label">Pays : <c:out value="${author.nativeCountry}"/></p>
<br />
<a href="<c:url value='/authors/edit?author-id=${author.id}'/>">Editer</a>&nbsp;&nbsp;|&nbsp;
<a href="<c:url value='/authors'/>">Retour Catalogue auteur</a>