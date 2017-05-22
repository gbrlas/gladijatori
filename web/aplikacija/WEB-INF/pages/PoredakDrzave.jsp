<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/navbar.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="${pageContext.request.contextPath}/js/sorttable.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/changeCursor.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/spoiler.css">

<script>
	$(document).ready(function() {
		$("#drzave").change(function() {
			var drzava = $("#drzave option:selected").text();
			var rows = $('table tbody tr');
			var pokazi = rows.filter('.' + drzava.replace(/\s+/g, '')).show();
			rows.not(pokazi).hide();
		});
	});
</script>
</head>
<body>

	<div class="container" align="center">
		<h2 align="center">Poredak virtualne lige po državama</h2>
		<br> <select class="form-control" id="drzave" name="drzave">
			<c:forEach var="entry" items="${drzave}">
				<option>${entry}</option>
			</c:forEach>
		</select>
		<table class="table table-striped table-hover table-users sortable">
			<thead>
				<tr class="hoverable">
					<th class="hidden-phone">Ime ekipe</th>
					<th class="hidden-phone">Natjecatelj</th>
					<th class="hidden-phone">Bodovi</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="entry" items="${natjecatelji}">
					<tr class='hoverable ${entry.getDrzava().replaceAll("\\s+", "")}' hidden="true"
						onclick="document.location = '${pageContext.request.contextPath}/servleti/virt/prikaziVirtEkipu?id=${entry.getEkipa().getId()}'">
						<td class="hidden-phone">${entry.getEkipa().getNaziv()}</td>
						<td class="hidden-phone">${entry.getUsername()}</td>
						<td class="hidden-phone">${entry.getEkipa().getOstvareniBodovi()}</td>
					</tr>
					</c:forEach>
			</tbody>

		</table>

	</div>

</body>
</html>