<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String vrstaKorisnika = (String)request.getSession().getAttribute("TipKorisnika");
if(vrstaKorisnika==null || vrstaKorisnika.isEmpty()){
	vrstaKorisnika = "";
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Rukomatna liga</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
   <style>
#header {
  position: absolute;
   top: 0;
   width: 100%;
   margin: 0;
   left: 0px;
   margin-left: 0;
   background-color:black;
   height:50px;
 
}

#nav.navbar-nav.navbar-right li a {
    color: blue;
}
</style>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <span class="navbar-brand" style="color:yellow";>Rukometna liga</span>
    </div>
    <div>
      <ul class="nav navbar-nav">
        <li><a href="poredakRukLige">Poredak rukometne lige</a></li>
        <li><a href="rezRukLiga">Rezultati rukometne lige</a></li>
        <li><a href="klubovi">Klubovi</a></li>
         <% if (vrstaKorisnika.equals("NAT") || vrstaKorisnika.equals("ADMIN") || vrstaKorisnika.equals("SLUZB") || vrstaKorisnika.equals("TEHN")) { %>
         <li  class="dropdown"><a href="virtualnaLiga" class="dropdown-toggle" data-toggle="dropdown">Virtualna liga<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li ><a href="poredakvirtualnaLiga">Poredak virtualne lige</a></li>
                            <li><a href="sudioniciVirtLige">Sudionici virtualne lige</a></li>
                            <li><a href="virtekipe">Virtualne ekipe</a></li>
                       </ul>
                       </li>
        <% } %>
       <% if (vrstaKorisnika.equals("ADMIN")) { %>
        <li><a href="pocetnakong">Početna konfiguracija</a></li>
        <li><a href="simulacija">Simulacija</a></li>
        <li><a href="adminDodaj">Dodavanje Korisnika</a></li>
        <% } %>
        <% if (vrstaKorisnika.equals("SLUZB")) { %>
        <li><a href="definirajPocKonf">Definicija početne konfiguracije</a></li>
        <li><a href="definirajCijenuIgraca">Cijena igrača</a></li>
        <% } %>
        <% if (vrstaKorisnika.equals("TEHN")) { %>
        <li><a href="mvp">Proglašavanja MVP-a</a></li>
        <% } %>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <c:if test="${TipKorisnika==null}">
            <li><a href="registracijaParam"><span class="glyphicon glyphicon-user" style="color:white;"></span> <span style="color:white;">Registriraj se</span></a></li>
	        <li><a href="prijavaParam"><span class="glyphicon glyphicon-log-in" style="color:white;"></span> <span style="color:white;">Prijava</span></a></li>
	   </c:if>
	   <c:if test="${TipKorisnika!=null}">
	        <li><a href="odjavaParam"><span class="glyphicon glyphicon-log-in" style="color:white;"></span> <span style="color:white;">Odjava</span></a></li>
	   </c:if>
      </ul>
    </div>
  </div>
</nav>
</body>
</html>