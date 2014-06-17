<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Managing Workers</title>
<link href="/Library/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<center><h1>Managing Worker Computers</h1></center>
<table class="container">
<tr class="container" valign="top">

<jsp:include page="/WEB-INF/navBar.jsp" />

<td class="bodyContainer">

<form action="/Library/AlterWorkers" method="post">
<p>${message }</p>
<table width="100%">
<tr>
	<td></td>
	<td>id</td>
	<td>URL</td>
	<td>Name</td>
	<td></td>
</tr>
<c:forEach var='i' items='${workerList.worker}'>
<tr>
	<td><input type="checkbox" name="Checked${i.arrayPlace}"></td>
<c:if test='${i.id>=0}'>	<td><a href="ManageWorkerSettings?place=${i.arrayPlace}">${i.id}</a></td></c:if>
<c:if test='${i.id<0}'>  <td>${i.id}</td></c:if>
	<td><input type="text" name="URL${i.arrayPlace}" value="${i.location}"></td>
	<td><input type="text" name="Name${i.arrayPlace}" value="${i.name}"></td>
	<td><input type="submit" name="Reset${i.arrayPlace}" value="Reset Worker"></td>
</tr>
</c:forEach>
</table>
<input type="submit" name="add" value="Add">
<input type="submit" name="delete" value="Delete">
<input type="submit" name="commit" value="Commit Changes">
<input type="submit" name="cancel" value="Cancel">
</form>
</td>
</tr>
</table>
</body>
</html>