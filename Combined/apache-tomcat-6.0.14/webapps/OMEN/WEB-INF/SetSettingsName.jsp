<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Name Feature Settings</title>
</head>
<body bgcolor="CCCCFF">
<h1>Name Feature Settings</h1>
<p>${SettingsError}
<FORM action="/OMEN/SaveSettings" METHOD="POST">
<p>Name for the settings: <input type="text" name="name" /></p>
<p><input type="submit" value="submit" /></p>
</FORM>
</body>
</html>