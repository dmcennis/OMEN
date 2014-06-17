<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/OMEN/style.css" rel="stylesheet" type="text/css">
<title>Problem with Analysis Submission</title>
</head>
<body>
<center><h1>Researcher Homepage</h1></center>
<table class="container">
<tr class="container" valaign="top">

<jsp:include page="/WEB-INF/researcherNavBar.jsp" />

	<td class="bodyContainer" valign="top" >
	<h2><center>Missing Information</center></h2>
	<p>The analysis result can not be completed because some needed info is missing</p>
	<p>${error}
	</td>
</tr>
</table>
</body>
</html>