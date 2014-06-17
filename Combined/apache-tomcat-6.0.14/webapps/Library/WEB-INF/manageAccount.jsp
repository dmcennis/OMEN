<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/Library/style.css" rel="stylesheet" type="text/css">
<title>Manage Accounts</title>
</head>
<body>
<table class="container">
<tr class="container" valign="top">

<jsp:include page="/WEB-INF/navBar.jsp" />

<td class="bodyContainer">
<form action="/Library/AlterAccounts" method="post">
<h2>Manage Accounts</h2>
<p>${message }</p>

<h3>Modidfy Current Account</h3>
<table>
<tr>
<td>Username:</td>
<td>${user}</td>
</tr>
<tr>
<td>Password:</td>
<td><input type="password" name="myPassword"></td>
</tr>
<tr>
<td>Password:</td>
<td><input type="password" name="myPassword2"></td>
</tr>
</table>
<input type="submit" name="changeMyPassword" value="Change Password">
<br><br>
<h3>Managing All Other Accounts</h3>
<table>
<tr>
	<td align="center"><b>Username</b></td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
</tr>
<c:forEach var='i' items='${librarian}'>
<tr>
	<td>${i}</td>
	<td><input type="submit" name="Password${i}" value="Reset Password"></td>
	<td><input type="submit" name="Delete${i}" value="Delete"></td>
</tr>
</c:forEach>
</table>
<br><br>
<h3>Add Librarian</h3>
<table>
<tr>
<td>Username:</td>
<td><input type="text" name="addUsername"></td>
</tr>
<tr>
<td>Password:</td>
<td><input type="password" name="addPassword"></td>
</tr>
<tr>
<td>Password:</td>
<td><input type="password" name="addPassword2"></td>
</tr>
</table>
<input type="submit" name="add" value="Add User">
<input type="submit" name="cancel" value="Cancel">
</form>
</td>
</tr>
</table>
</body>
</html>
