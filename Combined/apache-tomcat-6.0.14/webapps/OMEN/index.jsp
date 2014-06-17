<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body bgcolor="CCCCFF">
<h1>Researcher Login</h1>
<p>If you have already registered, enter your username and password below.
<form action="/OMEN/DoLogin" METHOD="POST">
<p>username: <input type="text" name="user">
<p>password: <input type="password" name="password">
<p><input type="submit" value="Log In">
<br>
<p>New researcher? <a href="researcherRegister.jsp">register here</a>
</form>
</body>
</html>