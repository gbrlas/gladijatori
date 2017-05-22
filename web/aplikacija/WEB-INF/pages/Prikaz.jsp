<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <body>
  <a href="dodaj">Make a new player</a>
  <c:choose>
  	
    <c:when test="${korisnik==null}">
      Nema unosa!
    </c:when>
    <c:otherwise>
      <h1><c:out value="${korisnik.ime}"/></h1>
      <p><c:out value="${korisnik.prezime}"/></p>
    </c:otherwise>
  </c:choose>

  </body>
</html>