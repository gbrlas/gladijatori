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
</head>
<body>
 <h3 align="center"><font color="red"><%=poruka %></font></h3>

</body>
</html>