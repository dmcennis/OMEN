<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/OMEN/style.css" rel="stylesheet" type="text/css">
<title>Delete Entries</title>
</head>
<body>
<center><h1>Delete Entries</h1></center>
<table class="container">
<tr class="container" valaign="top">

<jsp:include page="/WEB-INF/researcherNavBar.jsp" />

<td class="bodyContainer" valign="top" >
<form action="/OMEN/Delete" method="POST">
<center><h2>Select which entries to delete.</h2></center>
<br>
<center><h3>Saved Queries</h3></center>
<table width="100%">
	<tr align="center">
		<c:forEach var="i" items="${status.queryNames}">
			<td align="center"><input type="checkbox" name="query" value="${i}">${i}</td>
		</c:forEach>
	</tr>
</table>
<br>
<center><h3>Saved Settings</h3>
<table width="100%">
	<tr align="center">
		<c:forEach var="j" items="${status.settingNames}">
			<td align="center"><input type="checkbox" name="settings" value="${j}">${j}</a></td>
		</c:forEach> 
	</tr>
</table>
<br>
<center><h3>Saved Results</h3></center>
<table width="100%">
	<tr align="center">
		<c:forEach var="k" items="${status.resultNames}">
			<td align="center"><input type="checkbox" name="results" value="${k}">${k}</a></td>
		</c:forEach>
	</tr>
</table>
<br>
<input type="submit" name="Delete" value="Delete">
<input type="submit" name="Cancel" value="Cancel">
</td>
</tr>
</table>
</body>
</html>