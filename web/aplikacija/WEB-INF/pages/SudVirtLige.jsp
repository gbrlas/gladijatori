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
<h2 align="center">Sudionici virtualne lige</h2>
<br>
<table class="table table-striped table-hover table-users sortable">
    			<thead>
    				<tr class="hoverable">
    					<th class="hidden-phone">Korisničko ime</th>
    					<th class="hidden-phone">Naziv ekipe</th>
    					<th class="hidden-phone">Država natjecatelja</th>
    					<th class="hidden-phone">Podržani klub</th>
    					<th class="hidden-phone">Bodovi</th>
    				</tr>
    			</thead>   			
    				<tbody>
    				<c:forEach var="entry" items="${sudionici}">
    				<tr>
    					<td class="hidden-phone">${entry.getUsername()}</td>
    					<td class="hidden-phone hoverable" onclick="document.location = '${pageContext.request.contextPath}/servleti/virt/prikaziVirtEkipu?id=${entry.getEkipa().getId()}'">${entry.getEkipa().getNaziv()}</td>
                        <td class="hidden-phone">${entry.getDrzava()}</td>
                        <td class="hidden-phone hoverable" onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziKlub?id=${entry.getPodrzani().getId()}'">${entry.getPodrzani().getIme()}</td>                        
                        <td class="hidden-phone">${entry.getEkipa().getOstvareniBodovi()}</td>
                    </tr>
						</c:forEach>
	               </tbody>
    			
    			</table>

</div>

</body>
</html>