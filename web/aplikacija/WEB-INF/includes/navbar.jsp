<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="hr.gladijatori.modeli.korisnik.Korisnik"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String vrstaKorisnika = "";
	if (request.getSession().getAttribute("korisnik") != null) {
		vrstaKorisnika = (String) ((Korisnik) request.getSession().getAttribute("korisnik")).getTip()
				.toString();
	}
%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/navbar.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/nav.js"></script>

<title>Rukometna liga</title>

</head>
<body>

	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a href="${pageContext.request.contextPath}"
				class="hoverable nav navbar-brand" style="color: yellow">Rukometna
				liga</a>
		</div>
		<div>
			<ul class="nav navbar-nav">
				<li><a
					href="${pageContext.request.contextPath}/servleti/poredakRukLige">Poredak
						rukometne lige</a></li>
				<li><a
					href="${pageContext.request.contextPath}/servleti/rezRukLiga">Rezultati
						rukometne lige</a></li>
				<%
					if (vrstaKorisnika.equals("NAT") || vrstaKorisnika.equals("ADMIN") || vrstaKorisnika.equals("SLUZB")
							|| vrstaKorisnika.equals("TEHN")) {
				%>
				<li class="dropdown"><a href="virtualnaLiga"
					class="dropdown-toggle" data-toggle="dropdown">Virtualna liga<b
						class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a
							href="${pageContext.request.contextPath}/servleti/virt/poredakvirtualnaLiga">Poredak
								virtualne lige</a></li>
						<li><a
							href="${pageContext.request.contextPath}/servleti/virt/poredakPoKlubovima">Poredak
								po podržanim klubovima</a></li>
						<li><a
							href="${pageContext.request.contextPath}/servleti/virt/poredakPoDrzavama">Poredak
								po državama</a></li>
						<li><a
							href="${pageContext.request.contextPath}/servleti/virt/sudioniciVirtLige">Sudionici
								virtualne lige</a></li>
					</ul></li>
				<%
					}
				%>
				<%
					if (vrstaKorisnika.equals("NAT")) {
				%>
				<li class="dropdown"><a href="natjecatelj"
					class="dropdown-toggle" data-toggle="dropdown">Natjecatelj<b
						class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a
							href="${pageContext.request.contextPath}/servleti/nat/mojaEkipa">Moja
								ekipa</a></li>
						<li><a
							href="${pageContext.request.contextPath}/servleti/nat/odaberiVirtEkipu">Odabir
								virtualne ekipe</a></li>
					</ul></li>
				<%
					}
				%>
				<%
					if (vrstaKorisnika.equals("ADMIN")) {
				%>
				<li class="dropdown"><a href="admin" class="dropdown-toggle"
					data-toggle="dropdown">Admin<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a
							href="${pageContext.request.contextPath}/servleti/admin/pocetnakonf">Početna
								konfiguracija</a></li>
						<li><a
							href="${pageContext.request.contextPath}/servleti/admin/odabirzasim">Simulacija</a></li>
						<li><a
							href="${pageContext.request.contextPath}/servleti/admin/adminDodaj">Dodavanje
								korisnika</a></li>
						<li><a
							href="${pageContext.request.contextPath}/servleti/admin/restartLige">Restart
								lige</a></li>
						<li><a
							href="${pageContext.request.contextPath}/servleti/admin/ucitajNatjecatelje">Učitaj
								natjecatelje </a></li>
					</ul></li>
				<%
					}
				%>
				<%
					if (vrstaKorisnika.equals("SLUZB")) {
				%>
				<li class="dropdown"><a href="sluzb" class="dropdown-toggle"
					data-toggle="dropdown">Sluzbena osoba<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a
							href="${pageContext.request.contextPath}/servleti/sluzb/definirajPocKonf">Definicija
								početne konfiguracije</a></li>
						<li><a
							href="${pageContext.request.contextPath}/servleti/sluzb/postaviCijeneParam">Postavi
								cijene igrača </a></li>
					</ul></li>
				<%
					}
				%>
				<%
					if (vrstaKorisnika.equals("TEHN")) {
				%>
				<li><a
					href="${pageContext.request.contextPath}/servleti/tehn/mvpParam">Proglašavanje
						MVP-a</a></li>
				<%
					}
				%>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${korisnik == null}">
					<li><a
						href="${pageContext.request.contextPath}/servleti/registracijaParam"><span
							class="glyphicon glyphicon-user" style="color: white;"></span> <span
							style="color: white;">Registriraj se</span></a></li>
					<li><a
						href="${pageContext.request.contextPath}/servleti/prijavaParam"><span
							class="glyphicon glyphicon-log-in" style="color: white;"></span>
							<span style="color: white;">Prijava</span></a></li>
				</c:if>
				<c:if test="${korisnik != null}">
					<li><a
						href="${pageContext.request.contextPath}/servleti/odjavaParam"><span
							class="glyphicon glyphicon-log-in" style="color: white;"></span>
							<span style="color: white;">Odjava</span></a></li>
				</c:if>
			</ul>
		</div>
	</div>
	</nav>


</body>
</html>

