<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../includes/navbar.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<script src="${pageContext.request.contextPath}/js/sorttable.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/changeCursor.css">
</head>
<c:if test="${not empty vrijednosti}">
	<div class="container">
		<h2 align="center">Učitana konfiguracija</h2>
		<br>
		<table class="table table-striped table-hover table-users">
			<c:forEach var="vrijednost" items="${vrijednosti}">
				<tr>
					<th class="hidden-phone">${vrijednost.key}</th>
					<td class="hidden-phone">${vrijednost.value}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</c:if>
<c:if test="${poruka != null}"><h3><font color="red">${poruka}</font></h3></c:if>

</body>
</html>