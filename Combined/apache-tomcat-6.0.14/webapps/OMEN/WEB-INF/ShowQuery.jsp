<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Query Results</title>
</head>
<body bgcolor="CCCCFF">
<center><h1>Query Results</h1></center>
<form action="/OMEN/DispatchQuery" method="POST">

<table border=1 width="100%">
<tr>
	<td bgcolor="CCCCCC" align="center">Selected</td>
	<td bgcolor="CCCCCC" align="center"><a href="">Name</a></td>
	<td bgcolor="CCCCCC" align="center"><a href="">Composer</a></td>
	<td bgcolor="CCCCCC" align="center"><a href="">Album</a></td>
	<td bgcolor="CCCCCC" align="center"><a href="">Type</a></td>
	<td bgcolor="CCCCCC" align="center"><a href="">Location</a></td>
	<td bgcolor="CCCCCC" align="center"><a href="">Genre</a></td>
</tr>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach var="i" items="${query.file}">
<tr>
	<c:choose>
		<c:when test="${i.checked}">
			<td bgcolor="FFFFFF"><input type="CHECKBOX" name="${i.id}" CHECKED ></td>
		</c:when>
		<c:otherwise>
			<td bgcolor="FFFFFF"><input type="CHECKBOX" name="${i.id}" ></td>
		</c:otherwise>
	</c:choose>
	<td bgcolor="FFFFFF">${i.track}</td>
	<td bgcolor="FFFFFF">${i.artist}</td>
	<td bgcolor="FFFFFF">${i.album}</td>
	<td bgcolor="FFFFFF">${i.type}</td>
	<td bgcolor="FFFFFF">${i.location}</td>
	<td bgcolor="FFFFFF">${i.genre }</td>
	
</tr>
</c:forEach>
	

 <!--  
		for(int i=0;i<data.length;++i){
			out.println("<tr>");
			out.println("	<td bgcolor=\"FFFFFF\" align=\"center\">");
			
			if(data[i].isChecked()){
				out.println("<input type=\"CHECKBOX\" name=\""+i+"\" CHECKED ></td>");
			}else{
				out.println("<input type=\"CHECKBOX\" name=\""+i+"\" ></td>");
			}
			out.println("   <td bgcolor=\"FFFFFF\">" + data[i].getTrack() + "</td>");
			out.println("	<td bgcolor=\"FFFFFF\">" + data[i].getArtist() + "</td>");
			out.println("	<td bgcolor=\"FFFFFF\">"+data[i].getAlbum()+"</td>");
			out.println("	<td bgcolor=\"FFFFFF\">"+data[i].getLocation()+"</td>");
			out.println("</tr>");
		} -->

</table>
<input type="submit" name="Refine" value="Refine Query">
<input type="submit" name="Remove" value="Remove Selected Items">
<input type="submit" name="SelectAll" value="Select All">
<input type="submit" name="Save" value="Save File List">
<input type="submit" name="Random" value="Get Random Subset">
<input type="submit" name="Cancel" value="Cancel">
</form>
</body>
</html>