<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="nl">
<head>
	<c:import url="/WEB-INF/JSP/head.jsp"><c:param name="title" value="Voorkeurtaal"/></c:import>
</head>
<body>
	<p>
		Je voorkeurtaal is 
		${inNederlands ? "Nederlands" : "geen Nederlands"}.
	</p>
</body>
</html>