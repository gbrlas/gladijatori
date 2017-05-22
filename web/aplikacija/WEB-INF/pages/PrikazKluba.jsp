<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../includes/navbar.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <script src="${pageContext.request.contextPath}/js/sorttable.js"></script>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/changeCursor.css">
</head>
<h2 align="center">${klub.getIme()}</h2>
<div class="container">
<h3 align="center">Igrači</h3>
<br>
<table class="table table-striped table-hover table-users sortable hoverable">
    			<thead>
    				<tr class="hoverable">
    					<th class="hidden-phone">Ime igrača</th>
    					<th class="hidden-phone">Prezime igrača</th>
    					<th class="hidden-phone">Broj dresa</th>
    					<th class="hidden-phone">Broj godina</th>
    					<th class="hidden-phone">Vrijednost igrača</th>
    					<th class="hidden-phone">Pozicija igrača</th>
    				</tr>
    			</thead>   			
    				<tbody>
    				<c:forEach var="entry" items="${klub.getIgraci()}">
					<tr class="hoverable" onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziIgraca?id=${entry.getId()}'">  
					  	<td class="hidden-phone">${entry.getIme() }</td>
    					<td class="hidden-phone">${entry.getPrezime() }</td>
    					<td class="hidden-phone">${entry.getBrojDresa() }</td>
                        <td class="hidden-phone">${entry.getGodine() }</td>
                        <td class="hidden-phone">${entry.getVrijednost() }</td>
                        <td class="hidden-phone">${entry.getPozicija().toString() }</td>
                 
                    </tr>
						</c:forEach>
	               </tbody>
    			
    			</table>

</div>
<br>
<br>
<div class="container">
<h3 align="center">Utakmice</h3>
<br>
<table class="table table-striped table-hover table-users sortable">
    			<thead>
    				<tr class="hoverable">
    					<th class="hidden-phone">Domaćin</th>
    					<th class="hidden-phone">Gost</th>
    					<th class="hidden-phone">Rezultat</th>
    				</tr>
    			</thead>   			
    				<tbody>
    				<c:forEach var="entry" items="${utakmice}">
					<tr class="hoverable" onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziUtakmicu?id=${entry.getId()}'">  
					  	<td class="hidden-phone">${entry.getDomacin().getIme() }</td>
    					<td class="hidden-phone">${entry.getGost().getIme() }</td>
    					<td class="hidden-phone">${entry.getRezultat() }</td>
                 
                    </tr>
						</c:forEach>
	               </tbody>
    			
    			</table>

</div>
</body>
</html>