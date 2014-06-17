<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>addFiles</title>
</head>
<body bgcolor="CCFFCC">

<center><h1>Adding Files</h1></center>

<br>
<form action="/Library/DispatcherAddFiles" method="post">
<table width="100%">
<tr>
	<td>Selection Flag</td>
	<td>File Location</td>
	<td>Track Name</td>
	<td>Album</td>
	<td>Artist</td>
	<td>Genre</td>
	<td>File Type</td>
</tr>
<c:forEach var='i' items='${fileList.records}'>
<tr>
	<td><input type="checkbox" name="Checked${i.arrayPlace}"></td>
	<td><input type="text" name="Path${i.arrayPlace}" value="${i.path}"></td>
	<td><input type="text" name="Track${i.arrayPlace}" value="${i.track}"></td>
	<td><input type="text" name="Album${i.arrayPlace}" value="${i.album}"></td>
	<td><input type="text" name="Artist${i.arrayPlace}" value="${i.artist}"></td>
	<td><input type="text" name="Genre${i.arrayPlace}" value="${i.genre }"></td>
	<td>
		<select name="Select${i.arrayPlace}">
			<option value="stereo wav 16kHz">stereo wav 16kHz</option>
			<option value="mono wav 16kHz">mono wav 16kHz</option>
			<option value="stereo wav 22.05kHz">stereo wav 22.05kHz</option>
			<option value="mono wav 22.05kHz">mono wav 22.05kHz</option>
			<option value="stereo wav 44.1kHz" selected>stereo wav 44.1kHz</option>
			<option value="mono wav 44.1kHz">mono wav 44.1kHz</option>
			<option value="stereo aiff 44.1kHz">stereo aiff 44.1kHz</option>
			<option value="mono aiff 44.1kHz">mono aiff 44.1kHz</option>
			<option value="stereo mp3 96kbps">stereo mp3 96kbs</option>
			<option value="mono mp3 96kbps"> mono mp3 96kbps</option>
			<option value="stereo mp3 128kbps" SELECTED>stereo mp3 128kbps</option>
			<option value="mono mp3 128kbps">mono mp3 128kbps</option>
			<option value="stereo mp3 160kpbd">stereo mp3 160kbps</option>
			<option value="mono mp3 160kbps">mono mp3 160kbps</option>
			<option value="stereo mp3 196kbps">stereo mp3 196kbps</option>
			<option value="mono mp3 196kbps">mono mp3 196kbps</option>
		</select>
	</td>
</tr>
</c:forEach>
</table>
<p>
<input type="submit" name="Add" value="Add Row">
<input type="submit" name="Remove" value="Remove Rows">
<input type="submit" name="Publish" value="Publish">
<input type="submit" name="Cancel" value="Cancel">
</p>
</form>
</body>
</html>