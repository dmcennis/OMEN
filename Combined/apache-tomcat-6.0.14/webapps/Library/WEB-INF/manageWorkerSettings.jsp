<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/Library/style.css" rel="stylesheet" type="text/css">
<title>Worker Settings</title>
</head>
<body>
<center><h1>Managing Worker Computer Settings</h1></center>
<table class="container">
<tr class="container" valign="top">

<jsp:include page="/WEB-INF/navBar.jsp" />

<td class="bodyContainer">

<form action="/Library/AlterWorkerSettings" method="post">
<p>
<h3>Worker ${worker.name }</h3>
</p>
<p>${error }</p>
<table>
<tr>
	<td>Priority</td>
	<td><select name="priority">
		<c:if test="${worker.priority==1}"> <option value="1" selected>1</option></c:if><c:if test="${worker.priority != 1}"><option value="1" >1</option></c:if>
		<c:if test="${worker.priority==2}"> <option value="2" selected>2</option></c:if><c:if test="${worker.priority != 2}"><option value="2" >2</option></c:if>
		<c:if test="${worker.priority==3}"> <option value="3" selected>3</option></c:if><c:if test="${worker.priority != 3}"><option value="3" >3</option></c:if>
		<c:if test="${worker.priority==4}"> <option value="4" selected>4</option></c:if><c:if test="${worker.priority != 4}"><option value="4" >4</option></c:if>
		<c:if test="${worker.priority==5}"> <option value="5" selected>5</option></c:if><c:if test="${worker.priority != 5}"><option value="5" >5</option></c:if>
		<c:if test="${worker.priority==6}"> <option value="6" selected>6</option></c:if><c:if test="${worker.priority != 6}"><option value="6" >6</option></c:if>
		<c:if test="${worker.priority==7}"> <option value="7" selected>7</option></c:if><c:if test="${worker.priority != 7}"><option value="7" >7</option></c:if>
		<c:if test="${worker.priority==8}"> <option value="8" selected>8</option></c:if><c:if test="${worker.priority != 8}"><option value="8" >8</option></c:if>
		<c:if test="${worker.priority==9}"> <option value="9" selected>9</option></c:if><c:if test="${worker.priority != 9}"><option value="9" >9</option></c:if>
		<c:if test="${worker.priority==10}"> <option value="10" selected>10</option></c:if><c:if test="${worker.priority != 10}"><option value="10" >10</option></c:if>
	</select></td>
</tr>
<tr>
	<td>Max Cache Size</td>
	<td><input type="text" name="maxCache" value="${worker.maxCacheSize }"></td>
</tr>
<tr>
	<td>Restrict to screensaver</td>
	<td>
	<select name="onIdle">
		<c:if test="${worker.onIdle==0}"><option value="0" selected>False</option></c:if><<c:if test="${worker.onIdle!=0}"><option value="0">False</option></c:if>
		<c:if test="${worker.onIdle==1}"><option value="1" selected>True</option></c:if><<c:if test="${worker.onIdle!=1}"><option value="1">True</option></c:if>
	</select>
	</td>
</tr>
</table>
<input type="submit" name="Commit" value="commit">
<input type="submit" name="Cancel" value="cancel">
</form>
</td>
</tr>
</body>
</html>