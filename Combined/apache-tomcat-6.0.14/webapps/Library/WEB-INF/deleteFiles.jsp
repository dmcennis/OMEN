<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Files</title>
</head>
<body bgcolor="CCFFCC">
<center><h1>Delete Files</h1></center>

<br>
<form action="/Library/DispatchDeleteFiles" method="post">
<table width="100%">
<tr>
	<td></td>
	<td>File ID</td>
	<td>Track Name</td>
	<td>Album</td>
	<td>Artist</td>
	<td>Genre</td>
	<td>File Type</td>
</tr>
<c:forEach var='i' items='${fileList.records}'>
<tr>
	<td><input type="checkbox" name="${i.arrayPlace }"></td>
	<td>${i.id}</td>
	<td>${i.track}</td>
	<td>${i.album}</td>
	<td>${i.artist}</td>
	<td>${i.genre }</td>
	<td>${i.type}</td>
</tr>
</c:forEach>
</table>
<p>
<input type="submit" name="Delete" value="Delete Selected Files">
<input type="submit" name="Cancel" value="Cancel">
</p>
</form>

</body>
</html>