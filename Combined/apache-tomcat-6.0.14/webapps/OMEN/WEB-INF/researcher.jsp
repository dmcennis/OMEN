<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/OMEN/style.css" rel="stylesheet" type="text/css">
<title>${user} Homepage</title>
</head>
<body>
<center><h1>${user}'s Status Page</h1></center>
<table class="container">
<tr class="container" valaign="top">

<jsp:include page="/WEB-INF/researcherNavBar.jsp" />

<!-- Main Content -->
	<td class="bodyContainer" valign="top" >
		<center><h2>Status</h2>
		<hr>
		<h3>Saved Queries</h3>
		<table width="100%">
		<c:forEach var="i" items="${queryNames}">
			<tr >
					<td><a href="/OMEN/ViewQuery?id=${i.id}">${i.name}</a></td>
					<td>${i.user }</td>
			</tr>
		</c:forEach>
		</table>
		<hr>
		<h3>Saved Settings</h3>
		<table width="100%">
		  	<c:forEach var="j" items="${settingNames}">
			<tr >
				<td ><a href="/OMEN/ViewSettings?id=${j.id}">${j.name}</a></td>
				<td>${j.user}</td>
			</tr>
			</c:forEach> 
		</table>
		<hr>
		<h3>Saved Results</h3>
		<table width="100%">
		<tr align="center">
			<c:forEach var="k" items="${resultNames}">
				<td align="center"><a href="/OMEN/ViewResults?id=${k.id}">${k.name}</a></td>
			</c:forEach>
		</tr>
		</table>
		<hr>
		<h3>Analysis in Progress</h3>
		<table width="100%">
		<tr align="center">
			<c:forEach var="l" items="${partialResultNames}">
				<td align="center"><a href="/OMEN/ViewIncompleteResults?id=${l.id}">${l.name}</a></td>
			</c:forEach>
		</tr>
		</table>
		</center>
	</td>
</tr>
</table>

</body>
</html>