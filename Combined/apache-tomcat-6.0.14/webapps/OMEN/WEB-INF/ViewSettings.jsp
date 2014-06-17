<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="omen.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/OMEN/style.css" rel="stylesheet" type="text/css">
<title>View Settings ${settings.name}</title>
</head>
<body>
<center><h1>Setting ${settings.name}</h1></center>
<table class="container">
<tr class="container" valaign="top">

<jsp:include page="/WEB-INF/researcherNavBar.jsp" />

<!-- Main Content -->
<td class="bodyContainer" valign="top" >
<table width="100%">
<tr>
	<td width="50%"><b>Window Size (samples):</b></td>
	<td width="50%"> ${settings.windowSize }</td>
</tr>
<tr>
	<td width="50%"><b>Window Overlap (0-1):</b></td>
	<td width="50%">${settings.windowOverlap }</td>
</tr>
<tr>
	<td width="50%"><b>Normalise Recording:</b></td>
	<td width="50%">${settings.normalise }</td>
</tr>
<tr>
	<td width="50%"><b>Save Features Every Window</b></td>
	<td width="50%">${settings.savePerWindow }</td>
</tr>
<tr>
	<td width="50%"><b>Save features over all Windows</b></td>
	<td width="50%">${settings.saveOverall }</td>
</tr>
<tr>
	<td width="50%"><b>Output Type</b></td>
	<td width="50%">${settings.type }</td>
</tr>
</table>

<br>
<h3>Features with Attribute Settings</h3>
<% Settings s = (Settings)request.getAttribute("settings");
	String[] fn = s.getFeatureNames();
	boolean[] fb = s.getFeatureActive();
	String[][] fa = s.getFeatureAttributes();
	for( int i=0;i<fn.length;++i){
		out.println("<br><br>");
		out.println("<table width=\"100%\"><tr>");
		out.println("<td width=\"75%\"><b>"+fn[i]+"</b></td>");
		out.println("<td width=\"25%\">"+fb[i]+"</td>");
		out.println("</tr></table>");
		if(fa[i].length>0){
			out.println("Attributes");
		}
		out.println("<table width=\"100%\">");
		for(int j=0;j<fa[i].length;++j){
			out.println("<tr><td width=\"10%\"></td><td>"+fa[i][j]+"</td></tr>");
		}
		out.println("</tr></table>");
	}
%>
<br><br><br>
<h3>Aggregators with Feature Names and Attributes</h3>
<% 
String[] aName = s.getAggregatorNames();
String[][] aFeature = s.getAggregatorFeatures();
String[][] aAttribute = s.getAggregatorAttributes();
for(int i=0;i<aName.length;++i){
	out.println("<br><br>");
	out.println("<table width=\"100%\"><tr>");
	out.println("<td width=\"100%\"><b>"+aName[i]+"</b></td>");
	out.println("</tr>");
	if(aFeature[i].length>0){
		out.println("<tr><td>Features</td></tr>");
		for(int j=0;j<aFeature[i].length;++j){
			out.println("<tr><td width=\"10%\">&nbsp;</td><td>"+aFeature[i][j]+"</td></tr>");
		}
	}
	if(aAttribute[i].length>0){
		out.println("<tr><td>Attributes</td></tr>");
		for(int j=0;j<aAttribute[i].length;++j){
			out.println("<tr><td width=\"10%\">&nbsp;</td><td>"+aAttribute[i][j]+"</td></tr>");
		}
	}
	out.println("</table>");
}
%>
</td>
</tr>
</table>
</body>
</html>