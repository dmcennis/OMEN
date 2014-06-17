<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reject Feature ${feature.name}</title>
</head>
<body>
<form action="/OMENAdmin/Protected/RejectFeature" method="post">
<h1>Reject Feature ${feature.name}</h1>
<p><h3>Reason for the rejection</h3></p>
<p>
<textarea name="reason" rows="40" cols="80"></textarea>
</p>
<p>
<input type="submit" name="send" value="Send"/>
<input type="submit" name="cancel" value="Cancel"/> 
</p>
</form>
</body>
</html>