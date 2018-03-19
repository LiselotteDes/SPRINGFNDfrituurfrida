<%@tag description="website menu" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
	<nav>
		<ul>
			<li><a href="<c:url value='/'/>">Welkom</a></li>
			<li><a href="<c:url value='/snacks/alfabet'/>">Snacks (alfabet)</a></li>
			<li><a href="<c:url value='/snacks/beginnaam'/>">Snacks (begin naam)</a></li>
			<li><a href="<c:url value='/sauzen/raaddesaus'/>">Saus Raden</a></li>
			<li><a href="<c:url value='/sauzen'/>">Sauzen</a></li>
			<li><a href="<c:url value='/voorkeurtaal'/>">Voorkeurtaal</a></li>
			<li><a href="<c:url value='/frieten/zoekdefriet'/>">Zoek de friet</a></li>
		</ul>
	</nav>
</header>