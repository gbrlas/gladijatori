<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../includes/navbar.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <script src="${pageContext.request.contextPath}/js/sorttable.js"></script>
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/changeCursor.css">
</head>

<body>


<div class="container">
<h2 align="center">Igrači kluba</h2>
<br>
<table class="table table-striped table-hover table-users sortable">
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
    				<c:forEach var="entry" items="${igracikluba }">
    				<tr>
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

<br>

</div>
</body>
</html>