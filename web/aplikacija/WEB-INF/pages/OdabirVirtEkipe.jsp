<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../includes/navbar.jsp"%>

<%
	String poruka = (String) request.getAttribute("poruka");
	if (poruka == null) {
		poruka = "";
	}
	String porukaUspjeh = (String) request.getAttribute("porukaUspjeh");
	if (porukaUspjeh == null) {
		porukaUspjeh = "";
	}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script src="${pageContext.request.contextPath}/js/sorttable.js"></script>
<script src="${pageContext.request.contextPath}/js/selectCB.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/changeCursor.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/spoiler.css">

<script>

$(document).ready(function(){
	
	$("input[type='radio']").click(function(){
		var selected = $("input[type='radio']:checked").map(function() {
			return this.value;
		}).get();
		
		var sum = 0;
		$.each(selected, function(index, value){
			alert("#" + value +" td:eq(2)");
			alert($("#" + value +" td:eq(2)").text());
			sum += parseFloat($("#" + value +" td:eq(2)").text());
		});
		
		$("#vrijednost").text(sum);

	});
	
})

</script>

</head>
<h2 align="center">Odabir Virtualne Ekipe</h2>
<br>
<div align="center">
<table class="table table-striped table-hover table-users sortable" style="align: center; width:50%">
	<thead>
		<tr align="center">
			<th><h2 align="center">Proračun</h2></th>
			<th><h2 align="center">Vrijednost igrača</h2></th>
		</tr>
	</thead>
	<tbody>
		<tr align="center">
			<td><h3 align="center" id="proracun">${proracun}</h3></td>
			<td><h3 align="center" id="vrijednost"></h3></td>
			
		</tr>
	</tbody>
</table>
</div>
<br>

<section class="ac-container container">
	<div>
		<input id="golman" name="accordion-1" type="checkbox" /> <label
			for="golman">Golmani</label>
		<article class="ac-large">
			<table class="table table-striped table-hover table-users sortable">
				<thead>
					<tr class="hoverable">
						<th class="hidden-phone">Klub</th>
						<th class="hidden-phone">Ime</th>
						<th class="hidden-phone">Vrijednost</th>
						<th class="hidden-phone">Bodovi</th>
						<th class="hidden-phone">Odaberi</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="entry" items="${golmani}">
						<tr id="${entry.getId()}">
							<td class="hidden-phone hoverable"
								onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziKlub?id=${entry.getKlub().getId()}'">${entry.getKlub().getIme() }</td>
							<td class="hidden-phone hoverable"
								onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziIgraca?id=${entry.getId()}'">${entry.getIme()}
								${entry.getPrezime()}</td>
							<td class="hidden-phone">${entry.getVrijednost() }</td>
							<td class="hidden-phone">${entry.getBodovi() }</td>
							<td class="hidden-phone"><input type="radio" name="golmani"
								value="${entry.getId()}" /></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</article>

	</div>

	<div>
		<input id="lvanjski" name="accordion-1" type="checkbox" /> <label
			for="lvanjski">Lijevi Vanjski</label>
		<article class="ac-large">

			<table class="table table-striped table-hover table-users sortable">
				<thead>
					<tr class="hoverable">
						<th class="hidden-phone">Klub</th>
						<th class="hidden-phone">Ime</th>
						<th class="hidden-phone">Vrijednost</th>
						<th class="hidden-phone">Bodovi</th>
						<th class="hidden-phone">Odaberi</th>


					</tr>
				</thead>
				<tbody>
					<c:forEach var="entry" items="${lvanjski}">
						<tr id="${entry.getId()}">
							<td class="hidden-phone hoverable"
								onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziKlub?id=${entry.getKlub().getId()}'">${entry.getKlub().getIme() }</td>
							<td class="hidden-phone hoverable"
								onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziIgraca?id=${entry.getId()}'">${entry.getIme()}
								${entry.getPrezime()}</td>
							<td class="hidden-phone">${entry.getVrijednost() }</td>
							<td class="hidden-phone">${entry.getBodovi() }</td>
							<td class="hidden-phone"><input type="radio" name="lvanjski"
								value="${entry.getId()}" /></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</article>

	</div>
	<div>
		<input id="dvanjski" name="accordion-1" type="checkbox" /> <label
			for="dvanjski">Desni Vanjski</label>
		<article class="ac-large">

			<table class="table table-striped table-hover table-users sortable">
				<thead>
					<tr class="hoverable">
						<th class="hidden-phone">Klub</th>
						<th class="hidden-phone">Ime</th>
						<th class="hidden-phone">Vrijednost</th>
						<th class="hidden-phone">Bodovi</th>
						<th class="hidden-phone">Odaberi</th>


					</tr>
				</thead>
				<tbody>
					<c:forEach var="entry" items="${dvanjski}">
						<tr id="${entry.getId()}">
							<td class="hidden-phone hoverable"
								onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziKlub?id=${entry.getKlub().getId()}'">${entry.getKlub().getIme() }</td>
							<td class="hidden-phone hoverable"
								onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziIgraca?id=${entry.getId()}'">${entry.getIme()}
								${entry.getPrezime()}</td>
							<td class="hidden-phone">${entry.getVrijednost() }</td>
							<td class="hidden-phone">${entry.getBodovi() }</td>
							<td class="hidden-phone"><input type="radio" name="dvanjski"
								value="${entry.getId()}" /></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</article>

	</div>
	<div>
		<input id="svanjski" name="accordion-1" type="checkbox" /> <label
			for="svanjski">Srednji Vanjski</label>
		<article class="ac-large">

			<table class="table table-striped table-hover table-users sortable">
				<thead>
					<tr class="hoverable">
						<th class="hidden-phone">Klub</th>
						<th class="hidden-phone">Ime</th>
						<th class="hidden-phone">Vrijednost</th>
						<th class="hidden-phone">Bodovi</th>
						<th class="hidden-phone">Odaberi</th>


					</tr>
				</thead>
				<tbody>
					<c:forEach var="entry" items="${svanjski}">
						<tr id="${entry.getId()}">
							<td class="hidden-phone hoverable"
								onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziKlub?id=${entry.getKlub().getId()}'">${entry.getKlub().getIme() }</td>
							<td class="hidden-phone hoverable"
								onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziIgraca?id=${entry.getId()}'">${entry.getIme()}
								${entry.getPrezime()}</td>
							<td class="hidden-phone">${entry.getVrijednost() }</td>
							<td class="hidden-phone">${entry.getBodovi() }</td>
							<td class="hidden-phone"><input type="radio" name="svanjski"
								value="${entry.getId()}" /></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</article>

	</div>
	<div>
		<input id="dkrilo" name="accordion-1" type="checkbox" /> <label
			for="dkrilo">Desna Krila</label>
		<article class="ac-large">

			<table class="table table-striped table-hover table-users sortable">
				<thead>
					<tr class="hoverable">
						<th class="hidden-phone">Klub</th>
						<th class="hidden-phone">Ime</th>
						<th class="hidden-phone">Vrijednost</th>
						<th class="hidden-phone">Bodovi</th>
						<th class="hidden-phone">Odaberi</th>


					</tr>
				</thead>
				<tbody>
					<c:forEach var="entry" items="${dkrila}">
						<tr id="${entry.getId()}">
							<td class="hidden-phone hoverable"
								onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziKlub?id=${entry.getKlub().getId()}'">${entry.getKlub().getIme() }</td>
							<td class="hidden-phone hoverable"
								onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziIgraca?id=${entry.getId()}'">${entry.getIme()}
								${entry.getPrezime()}</td>
							<td class="hidden-phone">${entry.getVrijednost() }</td>
							<td class="hidden-phone">${entry.getBodovi() }</td>
							<td class="hidden-phone"><input type="radio" name="dkrila"
								value="${entry.getId()}" /></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</article>

	</div>
	<div>
		<input id="lkrilo" name="accordion-1" type="checkbox" /> <label
			for="lkrilo">Lijeva Krila</label>
		<article class="ac-large">

			<table class="table table-striped table-hover table-users sortable">
				<thead>
					<tr class="hoverable">
						<th class="hidden-phone">Klub</th>
						<th class="hidden-phone">Ime</th>
						<th class="hidden-phone">Vrijednost</th>
						<th class="hidden-phone">Bodovi</th>
						<th class="hidden-phone">Odaberi</th>


					</tr>
				</thead>
				<tbody>
					<c:forEach var="entry" items="${lkrila}">
						<tr id="${entry.getId()}">
							<td class="hidden-phone hoverable"
								onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziKlub?id=${entry.getKlub().getId()}'">${entry.getKlub().getIme() }</td>
							<td class="hidden-phone hoverable"
								onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziIgraca?id=${entry.getId()}'">${entry.getIme()}
								${entry.getPrezime()}</td>
							<td class="hidden-phone">${entry.getVrijednost() }</td>
							<td class="hidden-phone">${entry.getBodovi() }</td>
							<td class="hidden-phone"><input type="radio" name="lkrila"
								value="${entry.getId()}" /></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</article>

	</div>
	<div>
		<input id="pivot" name="accordion-1" type="checkbox" /> <label
			for="pivot">Pivoti</label>
		<article class="ac-large">

			<table class="table table-striped table-hover table-users sortable">
				<thead>
					<tr class="hoverable">
						<th class="hidden-phone">Klub</th>
						<th class="hidden-phone">Ime</th>
						<th class="hidden-phone">Vrijednost</th>
						<th class="hidden-phone">Bodovi</th>
						<th class="hidden-phone">Odaberi</th>


					</tr>
				</thead>
				<tbody>
					<c:forEach var="entry" items="${pivoti}">
						<tr id="${entry.getId()}">
							<td class="hidden-phone hoverable"
								onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziKlub?id=${entry.getKlub().getId()}'">${entry.getKlub().getIme() }</td>
							<td class="hidden-phone hoverable"
								onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziIgraca?id=${entry.getId()}'">${entry.getIme()}
								${entry.getPrezime()}</td>
							<td class="hidden-phone">${entry.getVrijednost() }</td>
							<td class="hidden-phone">${entry.getBodovi() }</td>
							<td class="hidden-phone"><input type="radio" name="pivoti"
								value="${entry.getId()}" /></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</article>

	</div>

	<h3 align="center">
		<%
			if (poruka != null) {
		%>
		<font color="red"> <%=poruka%></font>

		<%
			}
		%>
	</h3>
	<div class="form-group" align="center">
		<button type="submit" class="btn btn-primary" style="width: 28%;"
			onclick="selectVirt()">Odaberi</button>
	</div>
</section>


</body>
</html>