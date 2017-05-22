<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../includes/navbar.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<script src="${pageContext.request.contextPath}/js/sorttable.js"></script>
  <script src="${pageContext.request.contextPath}/js/selectCB.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/changeCursor.css">
  

</head>
<body>

	<c:forEach var="kolo" items="${kola}">
		<div class="container">
			<h2 align="center">${kolo.getBrojKola()}.Kolo</h2>
			<br>
			<table class="table table-striped table-hover table-users sortable">
				<thead>
					<tr class="hoverable">
						<th class="hidden-phone">Datum odigravanja</th>
						<th class="hidden-phone">Domacin</th>
						<th class="hidden-phone">Gost</th>
						<th class="hidden-phone">Rezultat</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="utakmica" items="${kolo.getUtakmice()}">
					<tr class="hoverable" onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziUtakmicu?id=${utakmica.getId()}'">  
							<td class="hidden-phone">${utakmica.getDatumOdigravanja() }</td>
							<td class="hidden-phone">${utakmica.getDomacin().getIme() }</td>
							<td class="hidden-phone">${utakmica.getGost().getIme() }</td>
							<td class="hidden-phone">${utakmica.getRezultat()}</td>
						</tr>
					</c:forEach>
				</tbody>

			</table>

		</div>
		<br>
		<br>
	</c:forEach>


</body>
</html>