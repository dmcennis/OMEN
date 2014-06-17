<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Publishing ${feature.name}</title>
</head>
<body>
<h1>Uploading Compiled Class File</h1>
<p>Upload the compiled class file for this feature to begin its distribution in OMEN</p>
<p>
		<form action="/OMENAdmin/Protected/AcceptFeature" enctype="multipart/form-data" method="POST">
			<input type="FILE" name="filename">
			<input type="SUBMIT" value="submit">
			<input type="SUBMIT" value="cancel">
		</form>
</p>
</body>
</html>