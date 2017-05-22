<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String poruka = (String)request.getAttribute("poruka");
if(poruka==null){
	poruka = "";
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Registracija korisnika</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<div id="header">
			<div class="regLblPos">
				<a style="color:white" href="${pageContext.request.contextPath}/servleti/prijavaParam">
						<span class="glyphicon glyphicon-log-in"></span> Prijava
					</a>
			</div>
			<div class="homePagePos">
				<a style="color:white" href="${pageContext.request.contextPath}">
						Početna stranica
						</a>
			</div>
		</div>

		<br> <br> <br>
		<h2 align="center">Registracija korisnika</h2>
		<br>
		<form action="${pageContext.request.contextPath}/servleti/registracija" method="post" class="form-horizontal"
			role="form" data-toggle="validator" accept-charset="UTF-8"
			method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="ime">Ime:</label>
				<div class="col-sm-10">
					<input class="form-control" id="ime" name="ime"
						placeholder="Unesite ime" required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="prezime">Prezime:</label>
				<div class="col-sm-10">
					<input class="form-control" id="prezime" name="prezime"
						placeholder="Unesite prezime" required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Email:</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" id="email" name="email"
						placeholder="Unesite email" required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="korimereg">Korisničko
					ime:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="korimereg"
						name="korimereg" placeholder="Unesite korisničko ime" required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="sifrareg">Sifra:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="sifrareg"
						name="sifrareg" placeholder="Unesite šifru" required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="nazvirekip">Naziv
					virtualne ekipe:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="nazvirekip"
						name="nazvirekip" placeholder="Unesite naziv virtualne ekipe"
						required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="drzava">Država:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="drzava" name="drzava"
						placeholder="Unesite državu" required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="selvratar">Podržani
					klub:</label>
				<div class="col-sm-10">
					<select class="form-control" id="podrzKlub" name="podrzKlub">
						<c:forEach var="entry" items="${klubovi}">
							<option>${entry.getIme()}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<br>
			
			<div class="form-group">
						<h3 align="center"><font color="red"><%=poruka %></font></h3>
			
				<div class="row">
				
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-success" style="width: 18%;">Registriraj
							se</button>
						&nbsp;&nbsp;&nbsp; <a class="btn btn-danger" style="width: 18%;"
							href="${pageContext.request.contextPath}">Odustani</a>
					</div>
				</div>
			</div>
		</form>
	</div>

</body>
</html>
