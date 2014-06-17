<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="/OMENAdmin/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Researchers</title>
</head>
<body>
<h1>Manage Researchers</h1>
<table class="container">
<tr class="container" valign="top">

<jsp:include page="/WEB-INF/navBar.jsp" />

<td class="bodyContainer">
${status }
<br>
<p>
<form action="/OMENAdmin/Protected/AlterResearchers" method="post">
<table>
<tr>
	<td align="center"><b>Username</b></td>
	<td align="canter"><b>Email</b></td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
</tr>
<c:forEach var='i' items='${researcher}'>
<tr>
	<td>${i.id}</td>
	<td><input type="text" name="Email${i.arrayPlace }" value="${i.email }"></td>
	<td><input type="submit" name="Email${i.arrayPlace }"  value="Change Email"></td>
	<td><input type="submit" name="Password${i.arrayPlace }" value="Reset Password"></td>
	<td><input type="submit" name="Delete${i.arrayPlace }" value="Delete"></td>
</tr>
</c:forEach>
</table>
<input type="submit" name="Cancel" value="Cancel">
</form>
</td>
</tr>
</table>

</body>
</html>