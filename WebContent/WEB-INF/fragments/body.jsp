<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
	pageEncoding="UTF-8"
%>
<div id = "wrapper">
	<c:choose>
		<c:when test="${!empty user}">
            <c:choose>
            	<c:when test = "${actionName == 'catalog'}">
            		<jsp:include page="catalog.jsp" />
         		</c:when>
            	<c:when test = "${actionName == 'books'}">
            		<jsp:include page="books.jsp" />
         		</c:when>
               	<c:when test = "${actionName == 'importbooks'}">
            		<jsp:include page="importbooks.jsp" />
         		</c:when>
         		<c:when test = "${actionName == 'books:edit'}">
           			<jsp:include page="editBook.jsp" />
         		</c:when>
         		<c:when test = "${actionName == 'books:add'}">
          			<jsp:include page="addBook.jsp" />
         		</c:when>
         		<c:when test = "${actionName == 'books:read'}">
          			<jsp:include page="readBook.jsp">
          				<jsp:param value="${user}" name="user" />
					</jsp:include>
         		</c:when>
         		<c:when test = "${actionName == 'authors'}">
            		<jsp:include page="authors.jsp" />
         		</c:when>
         		<c:when test = "${actionName == 'authors:edit'}">
            		<jsp:include page="editAuthor.jsp" />
         		</c:when>
         		<c:when test = "${actionName == 'authors:add'}">
            		<jsp:include page="addAuthor.jsp" />
         		</c:when>
         		<c:when test = "${actionName == 'authors:read'}">
            		<jsp:include page="readAuthor.jsp">
						<jsp:param value="${user}" name="user" />
					</jsp:include>
         		</c:when>
            </c:choose>
         </c:when>
		<c:when test="${actionName == 'signin' || actionName == 'login'}">
			<jsp:include page="userForm.jsp">
				<jsp:param value="${actionName}" name="actionName" />
			</jsp:include>
		</c:when>
		<c:otherwise>
			<section>
				<article>
					TP Limoissa résumé...
				</article>
			</section>
		</c:otherwise>
	</c:choose>
</div>