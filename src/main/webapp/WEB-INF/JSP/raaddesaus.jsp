<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@taglib prefix="spring" uri="http://www.springframework.org/tags" %> --%>	<%-- Niet nodig! --%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="nl">
<head>
	<vdab:head title="Saus raden"/>
</head>
<body>
	<vdab:menu/>
	<h1>Saus raden</h1>
	<c:choose>
		<c:when test="${spel.gewonnen}">
			<p>U bent gewonnen, de saus was ${spel.saus}</p>
		</c:when>
		<c:when test="${spel.verloren}">
			<p>U bent verloren, de saus was ${spel.saus}</p>
		</c:when>
		<c:otherwise>
			<p>Te raden saus: ${spel.sausMetPuntjes}</p>
			<c:url value="/sauzen/raaddesaus" var="url"/>
			<form:form action="${url}" modelAttribute="raadDeSausForm" method="post" id="radenform">
				<form:label path="letter">letter: </form:label>
				<form:input path="letter" maxlength="1" size="1" autofocus="autofocus" required="required"/><br><br>
				<input type="submit" value="Raden" id="radenknop">
			</form:form>
			<script>
				document.getElementById("radenform").onsubmit = function() {
					document.getElementById("radenknop").disabled = true;
				}
			</script>
		</c:otherwise>
	</c:choose>
	<c:url value="/sauzen/raaddesaus/nieuwspel" var="url"/>
	<form:form action="${url}" method="post">
		<input type="submit" value="Nieuw spel">
	</form:form>
	<img src="<c:url value='/images/${spel.aantalVerkeerdePogingen}.png'/>" alt="${spel.aantalVerkeerdePogingen} verkeerde pogingen">
</body>
</html>