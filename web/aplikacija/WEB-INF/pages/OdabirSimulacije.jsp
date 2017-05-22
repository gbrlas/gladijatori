<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../includes/navbar.jsp"%>
<%
String poruka = (String)request.getAttribute("poruka");
if(poruka==null){
	poruka = "";
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<script src="${pageContext.request.contextPath}/js/sorttable.js"></script>
  <script src="${pageContext.request.contextPath}/js/selectCB.js"></script>
  <script src="${pageContext.request.contextPath}/js/selectAll.js"></script>

</head>
<body>

	<c:forEach var="kolo" items="${liga.getKola()}">
		<div class="container">
			<h2 align="center">${kolo.getBrojKola()}.Kolo</h2>
			<br>
			<table class="table table-striped table-hover table-users sortable">
				<thead>
					<tr class="hoverable">
						<th class="hidden-phone">Datum odigravanja</th>
						<th class="hidden-phone">Domacin</th>
						<th class="hidden-phone">Gost</th>
						<th class="hidden-phone">Simuliraj</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="utakmica" items="${kolo.getUtakmice()}">
						<tr>
							<td class="hidden-phone">${utakmica.getDatumOdigravanja() }</td>
							<td class="hidden-phone">${utakmica.getDomacin().getIme() }</td>
							<td class="hidden-phone">${utakmica.getGost().getIme() }</td>
							<td class="hidden-phone">
								<c:choose>
								<c:when test="${not utakmica.isZavrsena()}">
									<input name="${kolo.getBrojKola()}" type="checkbox" value="${utakmica.getId()}"/>
								</c:when>
								<c:otherwise>
									${utakmica.getRezultat()}
								</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
					<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><input type="checkbox" name="ignore" onclick="checkAll(this, ${kolo.getBrojKola()})">Označi kolo</></td>
					
					</tr>
				</tbody>

			</table>
			

		</div>
		<br>
		<br>
	</c:forEach>
 <h3 align="center"><font color="red"><%=poruka %></font></h3>
 <div class="form-group" align="center">        
  <button type="submit" class="btn btn-primary" style="width: 28%;" onclick="selectCB()">Simulacija</button>
</div>
</body>
</html>