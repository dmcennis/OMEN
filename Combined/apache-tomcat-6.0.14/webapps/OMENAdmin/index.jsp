<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="style.css" rel="stylesheet" type="text/css">
<title>Administrator Login</title>
</head>
<body bgcolor="FF9999">
<form action="/OMENAdmin/DoLogin" method="POST">
<table width="100%" height="100%">
<tr width="100%" height="5%">
	<td>&nbsp;</td>
</tr>
<tr width="100%" height="75%">
	<td width="40%">&nbsp;</td>
	<td width="60%"><h1>Administrator Login</h1><p>
		<table>
			<tr><td>Name:</td><td><input type="text" name="Username"></td></tr>
			<tr><td>Password: </td><td><input type="password" name="Password"></td></tr>
			<tr><td><input type="submit" name="submit" value="Submit"></td></tr>
		</table>
	</td>
</tr>
</table>
</form>

</body>
</html>