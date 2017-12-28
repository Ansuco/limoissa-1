<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
	pageEncoding="UTF-8"
%>
<header>
	<!-- Navigation -->
	<nav>
		<img id = "logo" class="roundedImage" src="http://www.formext.fr/wp-content/uploads/2017/10/logo_Formext_Montpellier.png" alt=""/>
		<div id = "menu">
			<jsp:include page="navigator.jsp">
				<jsp:param value="${user}" name="user" />
			</jsp:include>
		</div>
	</nav>
</header>