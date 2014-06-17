<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/OMEN/style.css" rel="stylesheet" type="text/css">
<title>Incomplete Results</title>
</head>
<body>
<center><h2>${ir.name}</h2></center>
<table class="container">
<tr class="container" valaign="top">

<jsp:include page="/WEB-INF/researcherNavBar.jsp" />

<!-- Main Content -->
<td class="bodyContainer" valign="top" >

<p><b>Total number of files to process</b>: ${ir.totalFileCount }
<br>
<br>
<b>Number of files processed to date</b>: ${ir.fileCount }

</td>
</tr>
</table>
</body>
</html>