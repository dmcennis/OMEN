<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Altering Files</title>
</head>
<body bgcolor="CCFFCC">
<center><h1>Altering Files</h1></center>

<br>
<form action="/Library/DispatcherChangeFiles" method="post">
<table width="100%">
<tr>
	<td>Path</td>
	<td>Track Name</td>
	<td>Album</td>
	<td>Artist</td>
	<td>Genre</td>
	<td>File Type</td>
	<td>&nbsp;</td>
</tr>
<c:forEach var='i' items='${fileList.records}'>
<tr>
	<td><input type="text" name="Path${i.arrayPlace}" value="${i.path}"></td>
	<td><input type="text" name="Track${i.arrayPlace}" value="${i.track}"></td>
	<td><input type="text" name="Album${i.arrayPlace}" value="${i.album}"></td>
	<td><input type="text" name="Artist${i.arrayPlace}" value="${i.artist}"></td>
	<td><input type="text" name="Genre${i.arrayPlace}" value="${i.genre }"></td>
	<td><input type="text" name="Select${i.arrayPlace}" value="${i.type}"></td>
	<td><input type="submit" name="Publish${i.arrayPlace}" value="Publish Change"></td>
</tr>
</c:forEach>
</table>
<p>
<input type="submit" name="Cancel" value="Cancel">
</p>
</form>

</body>
</html>