<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="style.css" rel="stylesheet" type="text/css">
<title>Research Homepage</title>
</head>
<body>
<center><h1>Uploading jAudio Settings</h1></center>
<table class="container">
<tr class="container" valaign="top">

<jsp:include page="/WEB-INF/researcherNavBar.jsp" />

<!-- Main Content -->
	<td class="bodyContainer" valign="top" >
		<center><h2>Instructions</h2></center>
		<p><ol>
			<li>Download jAudio (<a href="">here</a>)</li>
			<li>Select desired features and settings</li>
			<li>Select "Save Settings" from the File menu</li>
			<li>Upload Settings file using the form below</li>
		</ol>
		<form action="/OMEN/ReceiveSettings" enctype="multipart/form-data" method="POST">
			<input type="FILE" name="filename">
			<input type="SUBMIT">
		</form>
	</td>
</tr>
</table>

</body>
</html>