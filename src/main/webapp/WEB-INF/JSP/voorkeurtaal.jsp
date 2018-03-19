<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="nl">
<head>
	<vdab:head title="Voorkeurtaal"/>
</head>
<body>
	<vdab:menu/>
	<p>
		Je voorkeurtaal is 
		${inNederlands ? "Nederlands" : "geen Nederlands"}.
	</p>
</body>
</html>