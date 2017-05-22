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
<h2 align="center">Klubovi</h2>
<br>
<table class="table table-striped table-hover table-users sortable">
    			<thead>
    				<tr class="hoverable">
    					<th class="hidden-phone">Organak</th>
    					<th class="hidden-phone">Ime kluba</th>
    					<th class="hidden-phone">Vrijednost kluba</th>
    					<th class="hidden-phone">Ostvareni bodovi</th>
    				</tr>
    			</thead>   			
    				<tbody>
    				<c:forEach var="entry" items="${klubovi }">
					<tr class="hoverable" onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziKlub?id=${entry.getId()}'">  
    					<td class="hidden-phone">${entry.getOgranak() }</td>
    					<td class="hidden-phone">${entry.getIme() }</td>
    					<td class="hidden-phone">${entry.getVrijednostKluba() }</td>
                        <td class="hidden-phone">${entry.getOstvareniBodovi() }</td>
                    </tr>
						</c:forEach>
	               </tbody>
    			
    			</table>

</div>
</body>
</html>