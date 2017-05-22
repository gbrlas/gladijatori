<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../includes/navbar.jsp"%>

<%

String poruka = (String)request.getAttribute("poruka");
if(poruka==null){
	poruka = "";
}
String porukaUspjeh = (String)request.getAttribute("porukaUspjeh");
if(porukaUspjeh==null){
	porukaUspjeh = "";
}

%>

<!DOCTYPE html>
<html lang="en">
<head>
<script src="${pageContext.request.contextPath}/js/sorttable.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/changeCursor.css">
</head>
<div class="container">
	<h3 align="center">Igrači</h3>
	<br>
	<form method="post"
		action="${pageContext.request.contextPath}/servleti/sluzb/postaviCijeneIgraca">
		<table class="table table-striped table-hover table-users sortable">
			<thead>
				<tr class="hoverable">
					<th class="hidden-phone">Igrač</th>
					<th class="hidden-phone">Broj godina</th>
					<th class="hidden-phone">Klub</th>
					<th class="hidden-phone">Pozicija igrača</th>
					<th class="hidden-phone">Stara vrijednost igrača</th>
					<th class="hidden-phone">Nova vrijednost igrača</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="entry" items="${igraci}">
					<tr>
						<td class="hidden-phone hoverable"
							onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziIgraca?id=${entry.getId()}'">${entry.getIme()}
							${entry.getPrezime()}</td>
						<td class="hidden-phone">${entry.getGodine() }</td>
						<td class="hidden-phone hoverable"
							onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziKlub?id=${entry.getKlub().getId()}'">${entry.getKlub().getIme() }</td>
						<td class="hidden-phone">${entry.getPozicija().toString() }</td>
						<td class="hidden-phone">${entry.getVrijednost() }</td>
						<td class="hidden-phone"><input name="${entry.getId()}"
							type="number" min="1" value="${entry.getVrijednost()}"></td>

					</tr>
				</c:forEach>
			</tbody>

		</table>
		<h3>
  <% if (poruka!=null) { %>
   <font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <%="                 "+poruka %></font>
  
   <% } %>
   <% if (porukaUspjeh!=null) { %>
     <font color="green">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <%="                 "+porukaUspjeh %></font>
     <% } %>
  </h3>
<div align="center">
	<button type="submit" class="btn btn-primary" style="width: 28%;">Potvrdi
		vrijednosti!</button>
</div>
</form>
</div>

</body>
</html>