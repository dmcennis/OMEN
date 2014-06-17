<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/OMEN/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Query ${query.name}</title>
</head>
<body>
<center><h1>Query ${query.name}</h1></center>
<table class="container">
<tr class="container" valaign="top">

<jsp:include page="/WEB-INF/researcherNavBar.jsp" />

<!-- Main Content -->
<td class="bodyContainer" valign="top" >
<center><h3>File Descriptions</h3></center>
<table border=1 width="100%">
<tr>
	<td bgcolor="CCCCCC" align="center"><a href="">Name</a></td>
	<td bgcolor="CCCCCC" align="center"><a href="">Composer</a></td>
	<td bgcolor="CCCCCC" align="center"><a href="">Album</a></td>
	<td bgcolor="CCCCCC" align="center"><a href="">Location</a></td>
	<td bgcolor="CCCCCC" align="center"><a href="">Genre</a></td>
	<td bgcolor="CCCCCC" align="center"><a href="">Type</a></td>
</tr>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach var="i" items="${query.file}">
<tr>
	<td bgcolor="FFFFFF">${i.track}</td>
	<td bgcolor="FFFFFF">${i.artist}</td>
	<td bgcolor="FFFFFF">${i.album}</td>
	<td bgcolor="FFFFFF">${i.location}</td>
	<td bgcolor="FFFFFF">${i.genre}</td>
	<td bgcolor="FFFFFF">${i.type}</td>
</tr>
</c:forEach>

</td>
</tr>
</table>

</body>
</html>