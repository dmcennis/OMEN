<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/OMENAdmin/style.css" rel="stylesheet" type="text/css">
<title>Managing Data</title>
</head>
<body>
<table class="container">
<tr class="container" valign="top">

<jsp:include page="/WEB-INF/navBar.jsp" />

<td class="bodyContainer">

<h1>Managing Data</h1>
<form action="/OMENAdmin/Protected/AlterData" method="POST">
<h3>File Lists</h3>
<table border="1">
<tr>
<td>Name</td>
<td>User</td>
<td></td>
</tr>
<c:forEach var='i' items='${status.queries}'>
<tr>
<td>${i.name }</td>
<td>${i.user }</td>
<td><input type="submit" name="query${i.id }" value="Delete"></td>
</tr>
</c:forEach>
</table>
<br>
<h3>Settings</h3>
<table border="1">
<tr>
<td>Name</td>
<td>User</td>
<td></td>
</tr>
<c:forEach var='j' items='${status.settings}'>
<tr>
<td>${j.name }</td>
<td>${j.user }</td>
<td><input type="submit" name="setting${j.id }" value="Delete"></td>
</tr>
</c:forEach>
</table>
<br>
<h3>Results</h3>
<table border="1">
<tr>
<td>Name</td>
<td>User</td>
<td></td>
</tr>
<c:forEach var='k' items='${status.results}'>
<tr>
<td>${k.name }</td>
<td>${k.user }</td>
<td><input type="submit" name="result${k.id }" value="Delete"></td>
</tr>
</c:forEach>
</table>
<br>
<h3>Incomplete Results</h3>
<table border="1">
<tr>
<td>Name</td>
<td>User</td>
<td></td>
</tr>
<c:forEach var='l' items='${status.partialResults}'>
<tr>
<td>${l.name }</td>
<td>${l.user }</td>
<td><input type="submit" name="partialResult${l.id }" value="Delete"></td>
</tr>
</c:forEach>
</table>
</form>
</td>
</tr>
</table>
</body>
</html>