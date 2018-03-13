<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"
		 session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='nl'>
<head>
	<c:import url="/WEB-INF/JSP/head.jsp">
		<c:param name="title" value="Frituur Frida"/>
	</c:import>
</head>
<body>
	<c:import url="/WEB-INF/JSP/menu.jsp"/>
	<h1>Frituur Frida</h1>
	<h2>Vandaag zijn we ${sluitingsdag}</h2>
	<img src="images/${sluitingsdag}.png" alt="${sluitingsdag}">
	<h2>Adres</h2>
	${adres.straat} ${adres.huisNr}<br>
	${adres.gemeente.postcode } ${adres.gemeente.naam }
	<c:if test="${bezocht}">
		<h2>Welkom terug!</h2>
	</c:if>
</body>
</html>