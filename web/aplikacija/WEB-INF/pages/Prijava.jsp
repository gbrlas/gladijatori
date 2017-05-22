<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String poruka = (String) request.getAttribute("poruka");
	if (poruka == null) {
		poruka = "";
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Prijava korisnika</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/navbar.css">

<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


</head>
<body>

	<div class="container">
		<div id="header">
			<div class="regLblPos">
				<a style="color: white"
					href="${pageContext.request.contextPath}/servleti/registracijaParam"><span
					class="glyphicon glyphicon-user"></span> Registriraj se</a>
			</div>
			<div class="homePagePos">
				<a style="color: white" href="${pageContext.request.contextPath}">Početna
					stranica</a>
			</div>
		</div>

		<br> <br> <br>
		<h2 align="center">Prijava korisnika</h2>
		<br>
		<form id="loginForm"
			action="${pageContext.request.contextPath}/servleti/prijava"
			class="form-horizontal clearfix" role="form" method="post">

			<div class="form-group">
				<label class="control-label col-sm-2" for="korimelog">Korisničko
					ime:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="korimelog"
						name="korimelog" placeholder="Unesite korisničko ime" required autofocus>
				</div>
			</div>


			<div class="form-group">
				<label class="control-label col-sm-2" for="sifralog">Šifra:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="sifralog"
						name="sifralog" placeholder="Unesite šifru" required>
				</div>
			</div>
			<br>
			<div class="form-group">
				<div class="row">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary" style="width: 18%;">Prijavi
							se</button>
						&nbsp;&nbsp;&nbsp; <a class="btn btn-danger" style="width: 18%;"
							href="${pageContext.request.contextPath}">Odustani</a>
					</div>
				</div>
				<br>
				<h3>
					<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%="           " + "      " + poruka%></font>
				</h3>
			</div>


		</form>
	</div>

</body>
</html>
