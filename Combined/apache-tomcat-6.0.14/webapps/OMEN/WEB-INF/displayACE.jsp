<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/OMEN/style.css" rel="stylesheet" type="text/css">
<title>View Result ${result.name}</title>
</head>
<body>
<center><h1>Result ${result.name}</h1></center>
<table class="container">
<tr class="container" valaign="top">

<jsp:include page="/WEB-INF/researcherNavBar.jsp" />
<td class="bodyContainer" valign="top" >

	<h3>type:</h3> ${result.type}
	<p><a href="/OMEN/GetKey?id=${result.id}">feature descriptions</a></p>
	<p><a href="/OMEN/GetValue?id=${result.id}">feature values</a> - one file overall</p>
	<p><a href="/OMEN/GetValueZip?id=${result.id}">feature values zipped</a> - one file per piece in a zip file</p>
	<p>&nbsp;</p>
	<h3>File List</h3></p>
	<table>
	<tr>
		<td><b>Album</b></td>
		<td><b>Artist</b></td>
		<td><b>Track</b></td>
	</tr>
	<c:forEach var="i" items="${result.files}">
		<tr>
			<td>${i.album}</td>
			<td>${i.artist }</td>
			<td>${i.track }</td>
		</tr>
	</c:forEach>
	</table>
	
</td>
</tr>
</table>
</body>
</html>