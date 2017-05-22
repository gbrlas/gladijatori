<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/includes/navbar.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <script src="${pageContext.request.contextPath}/js/sorttable.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/changeCursor.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/spoiler.css">

</head>
<body>
<div class="container">
<h2 align="center">Poredak virtualne lige</h2>
<br>
<table class="table table-striped table-hover table-users sortable">
    			<thead>
    				<tr class="hoverable">
    					<th class="hidden-phone">Ime ekipe</th>
    					<th class="hidden-phone">Natjecatelj</th>
    					<th class="hidden-phone">Država natjecatelja</th>
    					<th class="hidden-phone">Bodovi</th>
    					<th class="hidden-phone">Podržani klub</th>
    				</tr>
    			</thead>   			
    				<tbody>
    				<c:forEach var="entry" items="${ekipe}">
    				<tr>
    					<td class="hidden-phone hoverable" onclick="document.location = '${pageContext.request.contextPath}/servleti/virt/prikaziVirtEkipu?id=${entry.getId()}'">${entry.getNaziv()}</td>
    					<td class="hidden-phone">${entry.getNatjecatelj().getUsername()}</td>
                        <td class="hidden-phone">${entry.getNatjecatelj().getDrzava()}</td>
                        <td class="hidden-phone">${entry.getOstvareniBodovi()}</td>
                        <td class="hidden-phone hoverable" onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziKlub?id=${entry.getNatjecatelj().getPodrzani().getId()}'">${entry.getNatjecatelj().getPodrzani().getIme()}</td>
                    </tr>
						</c:forEach>
	               </tbody>
    			
    			</table>

</div>

</body>
</html>