<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Set Result Name</title>
</head>
<body bgcolor="CCCCFF">
<h1>Set Result Name</h1>
<p><font color="red">${error}</font></p>
<form action="/OMEN/DoAnalysis" METHOD="POST">
<p>Name for result set: <input type="text" name="name"/></p>
<P><INPUT type="submit" name="Submit" value="Submit"/>
<INPUT type="submit" name="Cancel" value="Cancel"/></P>
</form>
</body>
</html>