<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
	pageEncoding="UTF-8"
%>
<header>
	<!-- Navigation -->
	<img id = "logo" class="roundedImage" src="http://www.formext.fr/wp-content/uploads/2017/10/logo_Formext_Montpellier.png" alt=""/>		
	<h1 id="projectname">Librairie Limoissa</h1>
	<div id = "menu">
		<jsp:include page="navigator.jsp">
			<jsp:param value="${user}" name="user" />
		</jsp:include>
	</div>	
</header>