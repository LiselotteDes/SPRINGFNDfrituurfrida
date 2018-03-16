<%@ page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> --%>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> --%>	<%-- Niet nodig! --%>
<!DOCTYPE html>
<html lang="nl">
<head>
	<c:import url="/WEB-INF/JSP/head.jsp">
		<c:param name="title" value="Zoek de friet"/>
	</c:import>
</head>
<body>
	<h1>Zoek de friet</h1>
	
	<c:url value="/frieten/zoekdefriet" var="url"/>
	<form action="${url}" method="post">
		<c:forEach items="${spel.deuren}" var="deur" varStatus="status">
			<button type="submit" name="index" value="${status.index}">
				<c:choose>
					<c:when test="${deur.open}">
						<c:choose>
							<c:when test="${deur.metFriet}">
								<img src="<c:url value='/images/gevonden.png'/>" alt="gevonden">
							</c:when>
							<c:otherwise>
								<img src="<c:url value='/images/deuropen.png'/>" alt="deur open">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img src="<c:url value='/images/deurtoe.png'/>" alt="deur toe">
					</c:otherwise>
				</c:choose>
			</button>
		</c:forEach>
	</form>
	
	<c:url value="/frieten/zoekdefriet/nieuwspel" var="url"/>
	<form action="${url}" method="post">
		<input type="submit" value="Nieuw spel">
	</form>
</body>
</html>