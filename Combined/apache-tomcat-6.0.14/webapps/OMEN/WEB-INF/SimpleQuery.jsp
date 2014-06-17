<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/OMEN/style.css" rel="stylesheet" type="text/css">
<title>Simple Query</title>
</head>
<body>
<center><h1>Researcher Homepage</h1></center>
<table class="container">
<tr class="container" valaign="top">

	<jsp:include page="/WEB-INF/researcherNavBar.jsp" />

	<td class="bodyContainer" valign="top" >
		<center><h2>Keyword Search</h2></center>
		<form action="/OMEN/ExecuteQuery" method="post">
		<p>Either enter search terms or select all.
		<p><input type="text" name="query"></p>
		<p><input type="submit" name="Search" value="Search"> 
		<input type="submit" name="All" value="Select All"/></p>
		</form>
	</td>
	</tr>
</table>

</body>
</html>