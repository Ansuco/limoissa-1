<section id="show">
<form method="GET" action="<c:url value='/catalog'/>" id="form-sort-title-asc">
	<input type="hidden" value="title" name="sort" form="form-sort-title-asc" />
	<input type="hidden" value="asc" name="mode" form="form-sort-title-asc" />
	<input type="hidden" value="${currentPage}" name="page" form="form-sort-title-asc" />
</form>
<form method="GET" action="<c:url value='/catalog'/>" id="form-sort-title-desc">
	<input type="hidden" value="title" name="sort" form="form-sort-title-desc" />
	<input type="hidden" value="desc" name="mode" form="form-sort-title-desc" />
	<input type="hidden" value="${currentPage}" name="page" form="form-sort-title-desc" />
</form>
<form method="GET" action="<c:url value='/catalog'/>" id="form-sort-price-asc">
	<input type="hidden" value="price" name="sort" form="form-sort-price-asc" />
	<input type="hidden" value="asc" name="mode" form="form-sort-price-asc" />
	<input type="hidden" value="${currentPage}" name="page" form="form-sort-price-asc" />
</form>
<form method="GET" action="<c:url value='/catalog'/>" id="form-sort-price-desc">
	<input type="hidden" value="price" name="sort" form="form-sort-price-desc" />
	<input type="hidden" value="desc" name="mode" form="form-sort-price-desc" />
	<input type="hidden" value="${currentPage}" name="page" form="form-sort-price-desc" />
</form>
<form method="GET" action="<c:url value='/catalog'/>" id="form-sort-availability-asc">
	<input type="hidden" value="availability" name="sort" form="form-sort-availability-asc" />
	<input type="hidden" value="asc" name="mode" form="form-sort-availability-asc" />
	<input type="hidden" value="${currentPage}" name="page" form="form-sort-availability-asc" />
</form>
<form method="GET" action="<c:url value='/catalog'/>" id="form-sort-availability-desc">
	<input type="hidden" value="availability" name="sort" form="form-sort-availability-desc" />
	<input type="hidden" value="desc" name="mode" form="form-sort-availability-desc" />
	<input type="hidden" value="${currentPage}" name="page" form="form-sort-availability-desc" />
</form>
<table id="books">
	<tr>
		<th>Titre <input type="submit" value="&#9650;" class="hidden_button_style" form="form-sort-title-asc" /><input type="submit" value="&#9660;" class="hidden_button_style" form="form-sort-title-desc" /></th>
		<th>Prix <input type="submit" value="&#9650;" class="hidden_button_style" form="form-sort-price-asc" /><input type="submit" value="&#9660;" class="hidden_button_style" form="form-sort-price-desc" /></th>
		<th>Auteur(s)</th>
		<th>Disponibilité <input type="submit" value="&#9650;" class="hidden_button_style" form="form-sort-availability-asc" /><input type="submit" value="&#9660;" class="hidden_button_style" form="form-sort-availability-desc" /></th>
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
				<c:if test="${book.availability == true}">En stock</c:if>
				<c:if test="${book.availability == false}">En rupture</c:if>
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
<c:forEach begin="1" end="${noOfPages}" var="i">
	<c:choose>
		<c:when test="${currentPage eq i}">
			<td title="page ${i}">${i}</td>
		</c:when>
		<c:otherwise>
			<a title="page ${i}" href="<c:url value='/catalog?${(empty sort && empty mode ? "" : "sort="+=sort+="&mode="+=mode+="&")}page=${i}'/>">${i}</a>
		</c:otherwise>
	</c:choose>
</c:forEach>
</section>