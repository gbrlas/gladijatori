<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../includes/navbar.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <script src="${pageContext.request.contextPath}/js/sorttable.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/changeCursor.css">
    <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/magnific-popup.css">
</head>
<body>
<div class="container">
<h2 align="center">Poredak</h2>
<br>
<table class="table table-striped table-hover table-users sortable">
    			<thead>
    				<tr class="hoverable">
    					<th class="hidden-phone">Ogranak</th>
    					<th class="hidden-phone">Ime kluba</th>
    					<th class="hidden-phone">Ostvareni bodovi</th>
    					<th class="hidden-phone">Golovi</th>
    					<th class="hidden-phone">Primljeni</th>
    					<th class="hidden-phone">Razlika</th>
    				</tr>
    			</thead>   			
    				<tbody>
    				<c:forEach var="entry" items="${klubovi}">
    				<tr class="hoverable" onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziKlub?id=${entry.getId()}'">
    					<td class="hidden-phone">${entry.getOgranak() }</td>
    					<td class="hidden-phone">${entry.getIme() }</td>
                        <td class="hidden-phone">${entry.getOstvareniBodovi() }</td>
                        <td class="hidden-phone">${entry.getDaliGolova() }</td>
                        <td class="hidden-phone">${entry.getPrimiliGolova() }</td>
                        <td class="hidden-phone">${entry.getDaliGolova() - entry.getPrimiliGolova() }</td>
                    </tr>
						</c:forEach>
	               </tbody>
    			
    			</table>
</div>

</body>
</html>