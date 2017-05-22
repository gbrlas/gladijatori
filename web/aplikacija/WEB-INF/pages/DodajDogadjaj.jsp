<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/includes/navbar.jsp"%>

<%
	String poruka = (String) request.getAttribute("poruka");
	if (poruka == null) {
		poruka = "";
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Dodavanje događaja</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/navbar.css">
<%
	String imedomacin = (String) request.getAttribute("imedomacin");
	String imegost = (String) request.getAttribute("imegost");
	Long id = (Long)request.getAttribute("id");
%>

<body>

	<h1 align="center"><%=imedomacin%>-<%=imegost%></h1>
	<br>
	<form class="form-horizontal" method="post" action="/aplikacija/servleti/sluzb/spremiDog?id=<%= id%>">
		<div class="container">
			<div class="form-group">
				<label class="control-label col-sm-2" for="vrijemedogmin">Vrijeme
					događaja (min): </label>
				<div class="col-sm-10">
					<input type="number" step="1" value="0" max="59" min="0"
						class="form-control" id="vrijemedogmin" name="vrijemedogmin">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="vrijemedogsec">Vrijeme
					događaja (sec): </label>
				<div class="col-sm-10">
					<input type="number" step="1" value="0" max="59" min="0"
						class="form-control" id="vrijemedogsec" name="vrijemedogsec">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="igracDog">Igrač:
				</label>
				<div class="col-sm-10">
					<select class="form-control" id="igraciDog" name="igraciDog">
						<c:forEach var="entry" items="${igraci}">
							<option>${entry.getIme()} ${entry.getPrezime()}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="tipDog">Tip
					Događaja: </label>
				<div class="col-sm-10">
					<select class="form-control" id="tipDog" name="tipDog">
						<c:forEach var="entry" items="${dogadaji}">
							<option>${entry.toString() }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="col-sm-offset-2 col-sm-10">
				<button
					type="submit" class="btn btn-primary" style="width: 18%;">Zabilježi
					događaj</button>

			</div>
		</div>
	</form>
</body>
</html>
