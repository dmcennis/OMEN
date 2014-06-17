<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Researcher Registration</title>
</head>
<body bgcolor="CCCCFF">
<h1>Researcher Registration</h1>
To register provide the following information.  Please note, email addresses are only used to email your password if you forget it.  Place a false address at your peril.
<form action="/OMEN/DoRegister" method="POST">
<p>Desired Username: <input type="text" name="user">
<p>Desired Password: <input type="password" name="password1">
<p>Repeat Password:  <input type="password" name="password2">
<p>email address: <INPUT type="text" name="email">
<p><input type="submit">
</form>
</body>
</html>