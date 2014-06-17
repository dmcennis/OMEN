<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="omen.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Construct Analysis Request</title>
</head>
<body bgcolor="CCCCFF">
<h1>Construct Analysis Request</h1>
<p><font color="red">${error}</font></p>
<h3>Choose a file set and a settings file and a name for the request in order to construct an analysis request</h3>
<form action="/OMEN/PrepareAnalysis" method="POST">
<hr>

<table width="100%">
<tr>
	 <c:forEach var="i" items="${queryNames}">
		<td><input type="radio" name="fileset" value="${i.id}">${i.name} - ${i.user}</td>
	</c:forEach>

</tr> 
</table>
<hr>

<table width="100%">
<tr>
<c:forEach var="j" items="${settingNames}">
		<td><input type="radio" name="settings" value="${j.id}">${j.name} - ${j.user}</td>
	</c:forEach>

</tr>
</table>
<p>Name for request:<input type="text" name="name" value=""></p>
<p><input type="submit" name="Submit" value="Submit">
<input type="submit" name="Cancel" value="Cancel">
</form>
</P>
</body>
</html>