<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Set Query Name</title>
</head>
<body bgcolor="CCCCFF">
<h1>Set Name</h1>
<h3><font color="red">${SaveQueryError}</font></h3>
<form action="/OMEN/SaveQuery" method="POST">
<p>Name: <INPUT type="text" name="name">
<p><input type="submit" name="Save" value="Save">
<input type="submit" name="Cancel" value="Cancel">
</body>
</html>