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
<c:set var="count" value="0" scope="page" />
<c:set var="ukupno" value="0" scope="page" />
<h2 align="center">${virtEkipa.getNaziv()}</h2>
<h3 align="center">${virtEkipa.getNatjecatelj().getUsername()}</h3>
<br>
<div class="container">
	<h3 align="center">Igrači</h3>
	<br>
	<table
		class="table table-striped table-hover table-users sortable hoverable">
		<thead>
			<tr class="hoverable">
				<th class="hidden-phone">Ime igrača</th>
				<th class="hidden-phone">Broj godina</th>
				<th class="hidden-phone">Vrijednost igrača</th>
				<th class="hidden-phone">Pozicija igrača</th>
				<th class="hidden-phone">Ukupni bodovi</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="entry" items="${virtEkipa.getIgraci()}">
				<tr class="hoverable"
					onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziIgraca?id=${entry.getId()}'">
					<td class="hidden-phone">${entry.getIme()}
						${entry.getPrezime() }</td>
					<td class="hidden-phone">${entry.getGodine() }</td>
					<td class="hidden-phone">${entry.getVrijednost() }</td>
					<td class="hidden-phone">${entry.getPozicija().toString() }</td>
					<td class="hidden-phone">${entry.getBodovi() - virtEkipa.getPocetniBodovi().get(count)}</td>
				</tr>
				<c:set var="ukupno"
					value="${ukupno + entry.getBodovi() - virtEkipa.getPocetniBodovi().get(count)}"
					scope="page" />
				<c:set var="count" value="${count + 1}" scope="page" />
			</c:forEach>

		</tbody>
		<tfoot>
			<tr>
				<td class="hidden-phone"><b>UKUPNO</b></td>
				<td class="hidden-phone"></td>
				<td class="hidden-phone"></td>
				<td class="hidden-phone"></td>
				<td class="hidden-phone"><b>${ukupno}</b></td>

			</tr>
		</tfoot>
	</table>

</div>
</body>
</html>