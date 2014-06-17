<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/OMEN/style.css" rel="stylesheet" type="text/css">
<title>Submission Failed</title>
</head>
<body bgcolor="CCCCFF">
<center><h1>Submission Results</h1></center>
<table class="container">
<tr class="container" valaign="top">

<jsp:include page="/WEB-INF/researcherNavBar.jsp" />

	<td class="bodyContainer" valign="top" >
	<h2><center>Submission Unsuccesful</center></h2>
	<p>Please verify that the file uploaded is a valid Settings file.</p>
	<p>${error}
	</td>
</tr>
</table>

</body>
</html>