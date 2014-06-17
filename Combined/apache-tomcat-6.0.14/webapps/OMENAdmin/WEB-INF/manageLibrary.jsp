<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/OMENAdmin/style.css" rel="stylesheet" type="text/css">
<title>Manage Library Accounts</title>
</head>
<body>
<h1>Manage Library Accounts</h1>
<table class="container">
<tr class="container" valign="top">

<jsp:include page="/WEB-INF/navBar.jsp" />

<td class="bodyContainer">
<form action="/OMENAdmin/Protected/AlterLibraries" method="post">
<p>${status}
</p>
<table border-style="ridged" border-width="medium">
<tr>
	<td>ID</td>
	<td>Email</td>
	<td>Location</td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
</tr>
<c:forEach var='i' items='${library}'>
<tr>
	<td>${i.id }</td>
	<td><input type="text" name="Email${i.arrayPlace }" value="${i.email }"/></td>
	<td><input type="text" name="Location${i.arrayPlace }" value="${i.location }"/></td>
	<td><input type="submit" name="Submit${i.arrayPlace }" value="Submit Change"/></td>
	<td><input type="submit" name="Delete${i.arrayPlace }" value="Delete"/></td>
</tr>
</c:forEach>
</table>
<p>
<table>
<tr>
<td>ID:   <input type="text" name="id" value=""></td>
<td>Email:<input type="text" name="email" value=""></td>
<td>Location:<input type="text" name="location" value=""></td>
<td><input type="submit" name="Add" value="Add Library"></td>
</tr>
</table>
<input type="submit" name="Cancel" value="Cancel">
</form>
</td>
</tr>
</table>

</body>
</html>