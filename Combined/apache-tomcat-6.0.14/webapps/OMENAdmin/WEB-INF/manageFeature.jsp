<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/OMENAdmin/style.css" rel="stylesheet" type="text/css">
<title>Manage New Features</title>
</head>
<body>
<h1>Manage New Features</h1>
<table class="container">
<tr class="container" valign="top">

<jsp:include page="/WEB-INF/navBar.jsp" />

<td class="bodyContainer">

<p>${status}</p>

<form action="/OMENAdmin/Protected/ManagePublishFeature" method="post">
<h3>Features to be Added</h3>
<table>
<tr>
	<td><b>Feature Name</b></td>
	<td><b>Researcher</b></td>
	<td><b>Type</b></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
</tr>
<c:forEach var='i' items='${feature}'>
<tr>
	<td>${i.name }</td>
	<td>${i.rid }</td>
	<td>${i.type }</td>
	<td><input type="submit" name="view${i.arrayPlace}" value="View"></td>
	<td><input type="submit" name="download${i.arrayPlace}" value="Download"></td>
	<td><input type="submit" name="accept${i.arrayPlace}" value="Accept"></td>
	<td><input type="submit" name="reject${i.arrayPlace}" value="Reject"></td>
</tr>
</c:forEach>
</table>	
</form>
</td>
</tr>
</table>
</body>
</html>