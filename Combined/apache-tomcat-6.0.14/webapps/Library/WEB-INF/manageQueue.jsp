<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/Library/style.css" rel="stylesheet" type="text/css">
<title>Managing the Task Queue</title>
</head>
<body>
<center><h1>Managing the Task Queue</h1></center>
<table class="container">
<tr class="container" valign="top">

<jsp:include page="/WEB-INF/navBar.jsp" />

<td class="bodyContainer">
<p>${message}</p>
<form action="/Library/AlterQueue" method="post">
<h3>Currently Executing Jobs</h3>
<table width="100%">
<tr>
	<td>Task Id</td>
	<td>Task Name</td>
	<td>File Id</td>
	<td>Worker Name</td>
	<td></td>
	
</tr>
<c:forEach var='j' items='${jobs}'>
<tr>
	<td>${j.taskId }</td>
	<td>${j.taskName}</td>
	<td>${j.fileId }</td>
	<td>${j.workerName }</td>
	<td><input type="submit" name="delete${j.arrayPlace}" value="Cancel Analysis"></td>
</tr>
</c:forEach>
</table>
<hr>
<h3>Task Queue</h3>
<table width ="100%">
<tr>
	<td>Task ID</td>
	<td>Task Name</td>
	<td>File ID</td>
	<td>Direction to Move</td>
	<td>&nbsp;</td>
</tr>

<c:forEach var='q' items='${queue}'>
<tr>
	<td>${q.taskId }</td>
	<td>${q.taskName }</td>
	<td>${q.fileId }</td>
	<td><select name="Change${q.arrayPlace }">
		<option value="-8">Move Up 8 Places</option>
		<option value="-7">Move Up 7 Places</option>
		<option value="-6">Move Up 6 Places</option>
		<option value="-5">Move Up 5 Places</option>
		<option value="-4">Move Up 4 Places</option>
		<option value="-3">Move Up 3 Places</option>
		<option value="-2">Move Up 2 Places</option>
		<option value="-1">Move Up 1 Place</option>
		<option value="0" selected>Unchanged</option>
		<option value="1">Move Down 1 Place</option>
		<option value="2">Move Down 2 Places</option>
		<option value="3">Move Down 3 Places</option>
		<option value="4">Move Down 4 Places</option>
		<option value="5">Move Down 5 Places</option>
		<option value="6">Move Down 6 Places</option>
		<option value="7">Move Down 7 Places</option>
		<option value="8">Move Down 8 Places</option>
	</select></td>
	<td><input type="submit" name="move${q.arrayPlace}" value="Commit"></td>
</tr>
</c:forEach>

</table>
<input type="submit" name="cancel" value="Cancel">
</form>

</td>
</tr>
</table>
</body>
</html>