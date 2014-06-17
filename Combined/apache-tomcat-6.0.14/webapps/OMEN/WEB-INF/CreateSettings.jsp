<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/OMEN/style.css" rel="stylesheet" type="text/css">
<title>Define Settings</title>
</head>
<body>
<table class="container">
<tr class="container" valaign="top">

<jsp:include page="/WEB-INF/researcherNavBar.jsp" />

<!-- Main Content -->
<td class="bodyContainer" valign="top" >
<form action="/OMEN/DefineSettings" method="POST">
<Font color="red">${error }</Font>
<table width="100%">
<tr>
	<td width="40%"><b>Name for setting</b></td>
	<td width="60%"><input type="text" name="Name" value="${settings.name }"></td>
</tr>
<tr>
	<td width="40%"><b>Window Size (in samples)</b></td>
	<td width="60%"><input type="text" name="windowSize" value="${settings.windowSize }"/></td>
</tr>
<tr>
	<td width="40%"><b>Window Overlap</b></td>
	<td width="60%"><input type="text" name="windowOverlap" value="${settings.windowOverlap }"/></td>
</tr>
<tr>
	<td width="40%"><b>Sampling Rate</b></td>
	<td width="60%"><select name="samplingRate">
		<c:if test='${settings.samplingRate==8000.0}'><option value="8000.0" selected>8k</option></c:if>
		<c:if test='${settings.samplingRate!=8000.0}'><option value="8000.0">8k</option></c:if>
		<c:if test='${settings.samplingRate==11025.0}'><option value="11025.0" selected>11.025kk</option></c:if>
		<c:if test='${settings.samplingRate!=11025.0}'><option value="11025.0">11.025kk</option></c:if>
		<c:if test='${settings.samplingRate==16000.0}'><option value="16000.0" selected>16k</option></c:if>
		<c:if test='${settings.samplingRate!=16000.0}'><option value="16000.0">16k</option></c:if>
		<c:if test='${settings.samplingRate==22050.0}'><option value="22050.0" selected>22.05k</option></c:if>
		<c:if test='${settings.samplingRate!=22050.0}'><option value="22050.0">22.05k</option></c:if>
		<c:if test='${settings.samplingRate==44100.0}'><option value="44100.0" selected>44.1k</option></c:if>
		<c:if test='${settings.samplingRate!=44100.0}'><option value="44100.0">44.1k</option></c:if>
</tr>
<tr>
	<td width="40%"><b>normalise</b></td>
	<c:if test='${settings.normalise}'><td width="60%"><input type="checkbox" name="normalise" CHECKED></td></c:if>
	<c:if test='${!settings.normalise}'><td width="60%"><input type="checkbox" name="normalise"></td></c:if>
</tr>
<tr>
	<td width="40%"><b>Save features each window:</b></td>
	<c:if test='${settings.savePerWindow}'><td width="60%"><input type="checkbox" name="perWindowStat" CHECKED></td></c:if>
	<c:if test='${!settings.savePerWindow}'><td width="60%"><input type="checkbox" name="perWindowStat"></td></c:if>
</tr>
<tr>
	<td width="40%"><b>Save features over entire recording</b></td>
	<c:if test='${settings.saveOverall}'><td width="60%"><input type="checkbox" name="overallStats" CHECKED></td></c:if>
	<c:if test='${!settings.saveOverall}'><td width="60%"><input type="checkbox" name="overallStats" ></td></c:if>
</tr>
<tr>
	<td width="40%"><b>Output Type</b></td>
	<td width="60%">
		<select name="outputType">
		<c:if test='${settings.type=="ACE"}'><option value="ACE" selected>ACE</option></c:if>
		<c:if test='${settings.type!="ACE"}'><option value="ACE" >ACE</option></c:if>
		<c:if test='${settings.type=="ARFF"}'><option value="ARFF" selected>ARFF</option></c:if>
		<c:if test='${settings.type!="ARFF"}'><option value="ARFF">ARFF</option></c:if>
		</select>
	</td>
</tr>
</table>
<br>
<br>
<h3>Features</h3>
<b>For the default values of attributes, leave the entry blank.</b>
<table width="100%">
	<% omen.FeatureListing[] fl = (omen.FeatureListing[])session.getAttribute("features");
	for(int i=0;i<fl.length;++i){
		%>
	<tr>
		<td width="5%">
		<% if(fl[i].isActive()){ %>
		<input type="checkbox" name="<%out.println(fl[i].getName());%>" CHECKED>
		<%}else{ %>
		<input type="checkbox" name="<%out.println(fl[i].getName());%>">
		<%} %>
		</td>
		<td width="95%"><b><%out.println(fl[i].getName());%></b></td>
		<% for (int j=0;j<fl[i].getAttributes().length;++j){
			%>
			</tr>
			<tr>
				<td width="10%">&nbsp;</td>
				<td width="20%"><%out.println(fl[i].getAttributes(j)); %></td>
				<td width="70%" align="left"><input type="text" name="<%out.println(fl[i].getName()+j);%>" value="<%out.println(fl[i].getAttributeValues(j));%>"></td>
		<%} %>
	</tr>
	<tr>
	<td>&nbsp;</td>
	</tr>
	<%} %>
</table>
<br>
<table>
<c:forEach var='i' items='${aggregator}'>
<tr>
	<td width="65%"><b>${i.name }</b></td>
	<c:if test="${!(i.generic)}">
		<td width="15%">
		<select name="featureName${i.arrayPlace }">
			<c:forEach var='f' items='${features}'>
				<option value='${f.arrayPlace}'>${f.name }</option>
			</c:forEach>
		</select>
		</td>
		<td width="10%"><input type="submit" name='addFeature${i.arrayPlace}' value="Add Feature"></td>
	</c:if>
	<c:if test="${i.generic}">
		<td width="25%">&nbsp;</td>
	</c:if>
	<td width="10%"><input type="submit" name='deleteAgg${i.arrayPlace}' value="Delete"></td>
</tr>
	<c:forEach var='j' items='${i.features}'>
	<tr>
		<td width='10%'>&nbsp;</td>
		<td width='65%'>${j.name}</td>
		<td width="25%"><input type="submit" name='feature${j.arrayPlace}-${i.arrayPlace }' value="Remove Feature"></td>
	</tr>
	</c:forEach>
	<c:forEach var='k' items='${i.attributes}'>
	<tr>
		<td width="10%">&nbsp;</td>
		<td width="20%">${k.name }</td>
		<td width="70%"><input type="text" name="attribute${k.arrayPlace }-${i.arrayPlace}" value="${k.value}"></td>
	</tr>
	</c:forEach>
	<tr><td>&nbsp;</td></tr>
</c:forEach>
</table>
<p>
<select name="aggregatorType">
<c:forEach var='l' items='${aggregatorList}'>
<option value='${l.arrayPlace}'>${l.name }</option>
</c:forEach>
</select>
<input type="submit" name="aggregator" value="Add Aggregator">
</p>
<p>
<input type="submit" name="Submit" value="Submit">
<input type="submit" name="Cancel" value="Cancel">
</p>
</form>
</td>
</tr>
</table>
</body>
</html>