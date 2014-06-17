<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/OMEN/style.css" rel="stylesheet" type="text/css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>Extracted Feature Sets</title>
</head>
<body>
<center>Extracted Feature Sets</center>
<table class="container">
<tr class="container" valaign="top">

<jsp:include page="/WEB-INF/researcherNavBar.jsp" />

<!-- Main Content -->
<td class="bodyContainer" valign="top" >
<table>
<tr>
	<td><b>Name</b></td>
	<td><b>User</b></td>
	<td><b>Type</b></td>
</tr>
<c:forEach var="i" items="${analysis}">
	<tr>
		<td><a href="/OMEN/ViewResults?id=${id}">${i.name }</a></td>
		<td>${i.user }</td>
		<td>${i.type }</td>
	</tr>
</c:forEach>
</table>
</td>
</tr>
</table>
</body>
</html>