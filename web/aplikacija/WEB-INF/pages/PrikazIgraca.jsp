<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../includes/navbar.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <script src="${pageContext.request.contextPath}/js/sorttable.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/changeCursor.css">
</head>
<div class="container">
<h2 align="center">${igrac.getIme()} ${igrac.getPrezime()}</h2>
<br>
<table class="table table-striped table-hover table-users">
    				<tr class="hoverable" onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziKlub?id=${igrac.getKlub().getId()}'">
    					<th class="hidden-phone">Klub</th>
						<td class="hidden-phone">${igrac.getKlub().getIme() }</td>
					</tr>
					<tr>
    					<th class="hidden-phone">Ime</th>
						<td class="hidden-phone">${igrac.getIme() }</td>
    				</tr>
					<tr>
    					<th class="hidden-phone">Prezime</th>
 						<td class="hidden-phone">${igrac.getPrezime() }</td>
    				</tr>
					<tr>
    					<th class="hidden-phone">Ostvareni bodovi</th>
						<td class="hidden-phone">${igrac.getBodovi() }</td>
    				</tr>
    				<tr>
    					<th class="hidden-phone">Vrijednost</th>
						<td class="hidden-phone">${igrac.getVrijednost() }</td>
    				</tr>
					<tr>
    					<th class="hidden-phone">Pozicija</th>
						<td class="hidden-phone">${igrac.getPozicija().toString() }</td>
    				</tr>
  			
    			</table>

</div>
</body>
</html>