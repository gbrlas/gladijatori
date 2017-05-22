<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../includes/navbar.jsp" %>

<%

String poruka = (String)request.getAttribute("poruka");
if(poruka==null){
	poruka = "";
}
String porukaUspjeh = (String)request.getAttribute("porukaUspjeh");
if(porukaUspjeh==null){
	porukaUspjeh = "";
}

%>

<!DOCTYPE html>
<html lang="en">
<head>
  <script src="${pageContext.request.contextPath}/js/sorttable.js"></script>
</head>
<body>
<div class="container">
<h2 align="center">Definicija početne konfiguracije</h2>
  <br>
  <c:choose>
  <c:when test="${not def}">
  <form action="pockonfxlsx" class="form-horizontal" role="form"  data-toggle="validator" method="post" >
   <div class="form-group">
      <label class="control-label col-sm-2" for="proracun">Proračun:</label>
      <div class="col-sm-10">
        <input class="form-control" id="proracun" name="proracun" placeholder="Unesite proračun" required>
      </div>
    </div>
	 <div class="form-group">
      <label class="control-label col-sm-2" for="zgoditakDevet">Zgoditak unutar 9 m:</label>
      <div class="col-sm-10">
        <input  class="form-control" id="zgoditakDevet" name="zgoditakDevet" placeholder="Unesite vrijednost za zgoditak u 9 m" required>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="zgoditakSedam">Zgoditak sa 7 m:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="zgoditakSedam" name="zgoditakSedam" placeholder="Unesite vrijednost za zgoditak sa 7 m" required>
      </div>
    </div>
	<div class="form-group">
      <label class="control-label col-sm-2" for="zgoditakIzvan9">Zgoditak izvan 9 m:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" id="zgoditakIzvan9" name="zgoditakIzvan9" placeholder="Unesite vrijednost za zgoditak izvan 9 m" required>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="obranavratara">Obrana vratara:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" id="obranavratara" name="obranavratara" placeholder="Unesite vrijednost za obranu vratara" required>
      </div>
    </div>
     <div class="form-group">
      <label class="control-label col-sm-2" for="bloksuta">Blok šuta:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" id="bloksuta" name="bloksuta" placeholder="Unesite vrijednost za blok šuta" required>
      </div>
    </div>
     <div class="form-group">
      <label class="control-label col-sm-2" for="ukradenalopta">Ukradena lopta:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" id="ukradenalopta" name="ukradenalopta" placeholder="Unesite vrijednost za ukradenu loptu" required>
      </div>
    </div>
     <div class="form-group">
      <label class="control-label col-sm-2" for="obranavratara7">Obrana vratara 7 m:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" id="obranavratara7" name="obranavratara7" placeholder="Unesite vrijednost za obranu sa 7 m" required>
      </div>
    </div>
     <div class="form-group">
      <label class="control-label col-sm-2" for="mvp">Najbolji igrač utakmice:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" id="mvp" name="mvp" placeholder="Unesite vrijednost za najboljeg igrača" required>
      </div>
    </div>
     <div class="form-group">
      <label class="control-label col-sm-2" for="promasajSuta">Promašaj šuta:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" id="promasajSuta" name="promasajSuta" placeholder="Unesite vrijednost za promašaj" required>
      </div>
    </div>
     <div class="form-group">
      <label class="control-label col-sm-2" for="promasajSuta7">Promašaj šuta sa 7 m:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" id="promasajSuta7" name="promasajSuta7" placeholder="Unesite vrijednost za promašaj sa 7 m" required>
      </div>
    </div>
     <div class="form-group">
      <label class="control-label col-sm-2" for="izgubljenalopta">Izgubljena lopta:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" id="izgubljenalopta" name="izgubljenalopta" placeholder="Unesite vrijednost za izgubljenu loptu" required>
      </div>
    </div>
     <div class="form-group">
      <label class="control-label col-sm-2" for="primljenzgoditak">Primljen zgoditak:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" id="primljenzgoditak" name="primljenzgoditak" placeholder="Unesite vrijednost za primljen zgoditak" required>
      </div>
    </div>
     <div class="form-group">
      <label class="control-label col-sm-2" for="primljenzgoditak7">Primljen zgpoditak 7 m:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" id="primljenzgoditak7" name="primljenzgoditak7" placeholder="Unesite vrijednost za primljen zgoditak sa 7 m" required>
      </div>
    </div>
     <div class="form-group">
      <label class="control-label col-sm-2" for="iskljucenje2">Isključenje 2 min:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" id="iskljucenje2" name="iskljucenje2" placeholder="Unesite vrijednost za isključenje na 2 minute" required>
      </div>
    </div>
     <div class="form-group">
      <label class="control-label col-sm-2" for="trajnoiskljucenje">Trajno isključenje:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" id="trajnoiskljucenje" name="trajnoiskljucenje" placeholder="Unesite vrijednost za trajno isključenje" required>
      </div>
    </div>
     <div class="form-group">
      <label class="control-label col-sm-2" for="izravnoiskljucenje">Izravno isključenje:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" id="izravnoiskljucenje" name="izravnoiskljucenje" placeholder="Unesite vrijednost za trajno isključenje" required>
      </div>
    </div>
	  
    <div class="form-group">        
     <div class="row">
        <div class="col-sm-offset-2 col-sm-10">
    		<button type="submit" class="btn btn-primary" style="width: 28%;">Definicija pocetne konfiguracije</button>
    	</div>
    	</div>
  </div>
    </form>
  
  </c:when>
  <c:otherwise>
  <h3 align="center"><font color="red">Početna konfiguracija već učitana!</font></h3>
  </c:otherwise>
  </c:choose>
<h3 align="center">
  <% if (poruka!=null) { %>
   <font color="red">
   <%=poruka %></font>
  
   <% } %>
   <% if (porukaUspjeh!=null) { %>
   <font color="green">
   <%=porukaUspjeh %></font>
  
   <% } %>
  </h3>
    </div>
</body>
</html>