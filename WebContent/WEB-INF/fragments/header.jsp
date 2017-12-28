<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
	pageEncoding="UTF-8"
%>
<header>
	<!-- Navigation -->
	<nav>
		<img id = "logo" src="http://www.formext.fr/wp-content/uploads/2017/10/logo_Formext_Montpellier.png" alt="" style="float:left" />
		<div id = "menu" style="float:right">
			<jsp:include page="navigator.jsp">
				<jsp:param value="${user}" name="user" />
			</jsp:include>
		</div>
	</nav>
</header>