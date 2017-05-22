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

<div class="container">
	<h2 align="center">Moja Ekipa</h2>
	<br>
	<table class="table table-striped table-hover table-users sortable">
		<thead>
			<tr class="hoverable">
				<th class="hidden-phone">Ime i prezime</th>
				<th class="hidden-phone">Vrijednost</th>
				<th class="hidden-phone">Broj bodova</th>
				<th class="hidden-phone">Pozicija</th>
				<th class="hidden-phone">Klub</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entry" items="${igraci}">
				<tr>
					<td class="hidden-phone hoverable"
						onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziIgraca?id=${entry.getId()}'">${entry.getIme()}
						${entry.getPrezime()}</td>
					<td class="hidden-phone">${entry.getVrijednost()}</td>
					<td class="hidden-phone">${entry.getBodovi() - korisnik.getEkipa().getPocetniBodovi().get(count)}</td>
					<td class="hidden-phone">${entry.getPozicija().toString()}</td>
					<td class="hidden-phone hoverable"
						onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziKlub?id=${entry.getKlub().getId()}'">${entry.getKlub().getIme()}</td>
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
				<td class="hidden-phone"><b>${ukupno}</b></td>
				<td class="hidden-phone"></td>
				<td class="hidden-phone"></td>
			</tr>
			</tfoot>
	</table>

</div>
</body>
</html>