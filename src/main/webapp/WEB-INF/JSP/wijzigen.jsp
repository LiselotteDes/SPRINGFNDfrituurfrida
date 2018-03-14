<%@ page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="nl">
<head>
	<c:import url="/WEB-INF/JSP/head.jsp">
		<c:param name="title" value="${snack.naam} wijzigen" />
	</c:import>
</head>
<body>
	<h1>Snack wijzigen</h1>
	<spring:url value="/snacks/{id}/wijzigen" var="url"><spring:param name="id" value="${snack.id}"/></spring:url>
	
	<form:form action="${url}" modelAttribute="snack" method="post" id="wijzigenform">
		<form:label path="naam">Naam: <form:errors path="naam"/></form:label>
		<form:input path="naam" autofocus="autofocus" required="required" />
		<form:label path="prijs">Prijs: <form:errors path="prijs"/></form:label>
		<form:input path="prijs" required="required" type="number" min="0" step="0.01"/>
		<input type="submit" value="Opslaan" id="opslaanknop" />
	</form:form>
	
	<script>
		document.getElementById("wijzigenform").onsubmit = function() {
			document.getElementById("opslaanknop").disabled = true;
		}
	</script>
</body>
</html>