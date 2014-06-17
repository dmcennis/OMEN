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
		<center><h2>Account Management</h2></center>
		<form action="/OMEN/AlterAccount" method="post">
		<table>
		<tr><td>Password:</td><td><input type="password" name="password"></td></tr>
		<tr><td>Password:</td><td><input type="password" name="password2"></td></tr>
		<tr><td>Email:</td><td><input type="text" name="email" value="${email}"></td></tr>
		</table>
		<input type="submit" name="Save" value="Save"> 
		<input type="submit" name="Cancel" value="Cancel"/></p>
		</form>
	</td>
	</tr>
</table>

</body>
</html>