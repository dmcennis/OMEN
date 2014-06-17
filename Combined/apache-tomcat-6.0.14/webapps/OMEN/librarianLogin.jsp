<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Librarian Login Page</title>
</head>
<body bgcolor="CCFFCC">
<h1>Librarian Login</h1>
<p>Enter the 
<form action="/Dais/DoLibraryLogin" METHOD="POST">
<p>username: <input type="text" name="user">
<p>password: <input type="password" name="password">
<p><input type="submit" value="Log In">
<br>
<p>Want to link your digital library with Dais? <a href="librarianRegister.jsp">Register Here</a>
<p>Forgotten your password? Receive a reminder by <a href="LibrarianLostPassword">email</a>
</form>

</body>
</html>