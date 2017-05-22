<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../includes/navbar.jsp" %>

<%

String poruka = (String)request.getAttribute("poruka");
if(poruka==null){
	poruka = "";
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>

<div class="container">
<h2 align="center">Dodavanje novog korisnika</h2>
  <br>
  <form action="dodavanjeNovogKorisnika" method="post" class="form-horizontal" role="form"  data-toggle="validator" >
   <div class="form-group">
      <label class="control-label col-sm-2" for="ime">Ime:</label>
      <div class="col-sm-10">
        <input class="form-control" id="ime" name="ime" placeholder="Unesite ime" required>
      </div>
    </div>
	 <div class="form-group">
      <label class="control-label col-sm-2" for="prezime">Prezime:</label>
      <div class="col-sm-10">
        <input  class="form-control" id="prezime" name="prezime" placeholder="Unesite prezime" required>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Email:</label>
      <div class="col-sm-10">
        <input type="email" class="form-control" id="email" name="email" placeholder="Unesite email" required>
      </div>
    </div>
	<div class="form-group">
      <label class="control-label col-sm-2" for="korimereg">Korisničko ime:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" id="korimereg" name="korimereg" placeholder="Unesite korisničko ime" required>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="sifrareg">Sifra:</label>
      <div class="col-sm-10">          
        <input type="password" class="form-control" id="sifrareg" name="sifrareg" placeholder="Unesite šifru" required>
      </div>
    </div>
    
    <div class="form-group">    
       <label class="control-label col-sm-2" for="selKorisnik">Vrsta korisnika:</label>
	   <div class="col-sm-10">
      <select class="form-control" id="selKorisnik" name="selKorisnik">
       <option>Službena osoba</option>
       <option>Tehniča komisija</option>		
      </select>
	  </div>
	  </div>
	  
    	 <div class="form-group">        
     <div class="row">
        <div class="col-sm-offset-2 col-sm-10">
    		<button type="submit" class="btn btn-success" style="width: 18%;">Dodaj Korisnika</button>
    	</div>
  </div>
  <br>
  <h3><font color="green">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <%="                 "+poruka %></font></h3>
    </div>
  </form>
  </div>
</body>
</html>