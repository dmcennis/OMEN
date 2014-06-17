<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<title>New Feature Submission</title>
</head>
<body>

<center><h1>Uploading a New Feature</h1></center>
<table class="container">
<tr class="container" valaign="top">

<jsp:include page="/WEB-INF/researcherNavBar.jsp" />

<td class="bodyContainer" valign="top" >
<h3>Uploading a New Feature</h3>
<p>
Supply the name and description of the new feature here.  On the next page, upload the java file implementing the feature.  
The feature will then be reviewed by the OMEN administrator. An email notice will be sent once the new feature has been accepted or rejected.
<form action="/OMEN/UploadFeature" enctype="multipart/form-data" method="post">
<p><font color="red">${error}</font></p>
<input type="FILE" name="filename">
<input type="SUBMIT">
</form>
</p>
</td>
</tr>
</table>
</body>
</html>