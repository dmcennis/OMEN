<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/Library/style.css" rel="stylesheet" type="text/css">
<title>Managing Operating Hours</title>
</head>
<body>
<center><h1>Managing Operating Hours</h1></center>

<table class="container">
<tr class="container" valign="top">

<jsp:include page="/WEB-INF/navBar.jsp" />

<td class="bodyContainer">

<form action="/Library/AlterHours" method="post">

<table width="100%">
<tr>
	<td></td>
	<td></td>
	<td>day</td>
	<td>hours</td>
	<td>minute</td>
</tr>
<c:forEach var='i' items='${time.time}'>
<tr>
	<td><input type="checkbox" name="Checked${i.arrayPlace}"></td>
	<td><select name="Type${i.arrayPlace}">
		<c:choose>
			<c:when test="${i.type == 0}">
				<option value="0" selected>Start</option>
				<option value="1">Stop</option>
			</c:when>
			<c:otherwise>
				<option value="0">Start</option>
				<option value="1" selected>Stop</option>
			</c:otherwise>
		</c:choose>
		</select>
	</td>
	<td><select name="Day${i.arrayPlace}">
		<c:if test="${i.day == 1}"><option value="1" selected>Sunday</option></c:if><c:if test="${i.day != 1}"><option value="1">Sunday</option></c:if>
		<c:if test="${i.day == 2}"><option value="2" selected>Monday</option></c:if><c:if test="${i.day != 2}"><option value="2">Monday</option></c:if>
		<c:if test="${i.day == 3}"><option value="3" selected>Tuesday</option></c:if><c:if test="${i.day != 3}"><option value="3">Tuesday</option></c:if>
		<c:if test="${i.day == 4}"><option value="4" selected>Wedesday</option></c:if><c:if test="${i.day != 4}"><option value="4">Wedesnday</option></c:if>
		<c:if test="${i.day == 5}"><option value="5" selected>Thursday</option></c:if><c:if test="${i.day != 5}"><option value="5">Thursday</option></c:if>
		<c:if test="${i.day == 6}"><option value="6" selected>Friday</option></c:if><c:if test="${i.day != 6}"><option value="6">Friday</option></c:if>
		<c:if test="${i.day == 7}"><option value="7" selected>Saturday</option></c:if><c:if test="${i.day != 7}"><option value="7">Saturday</option></c:if>
	</select>
	</td>
	<td><select name="Hour${i.arrayPlace}">
		<c:if test="${i.hour==0}"><option value="0" selected>0</option></c:if><c:if test="${i.hour!=0}"><option value="0">0</option></c:if>
		<c:if test="${i.hour==1}"><option value="1" selected>1</option></c:if><c:if test="${i.hour!=1}"><option value="1">1</option></c:if>
		<c:if test="${i.hour==2}"><option value="2" selected>2</option></c:if><c:if test="${i.hour!=2}"><option value="2">2</option></c:if>
		<c:if test="${i.hour==3}"><option value="3" selected>3</option></c:if><c:if test="${i.hour!=3}"><option value="3">3</option></c:if>
		<c:if test="${i.hour==4}"><option value="4" selected>4</option></c:if><c:if test="${i.hour!=4}"><option value="4">4</option></c:if>
		<c:if test="${i.hour==5}"><option value="5" selected>5</option></c:if><c:if test="${i.hour!=5}"><option value="5">5</option></c:if>
		<c:if test="${i.hour==6}"><option value="6" selected>6</option></c:if><c:if test="${i.hour!=6}"><option value="6">6</option></c:if>
		<c:if test="${i.hour==7}"><option value="7" selected>7</option></c:if><c:if test="${i.hour!=7}"><option value="7">7</option></c:if>
		<c:if test="${i.hour==8}"><option value="8" selected>8</option></c:if><c:if test="${i.hour!=8}"><option value="8">8</option></c:if>
		<c:if test="${i.hour==9}"><option value="9" selected>9</option></c:if><c:if test="${i.hour!=9}"><option value="9">9</option></c:if>
		<c:if test="${i.hour==10}"><option value="10" selected>10</option></c:if><c:if test="${i.hour!=10}"><option value="10">10</option></c:if>
		<c:if test="${i.hour==11}"><option value="11" selected>11</option></c:if><c:if test="${i.hour!=11}"><option value="11">11</option></c:if>
		<c:if test="${i.hour==12}"><option value="12" selected>12</option></c:if><c:if test="${i.hour!=12}"><option value="12">12</option></c:if>
		<c:if test="${i.hour==13}"><option value="13" selected>13</option></c:if><c:if test="${i.hour!=13}"><option value="13">13</option></c:if>
		<c:if test="${i.hour==14}"><option value="14" selected>14</option></c:if><c:if test="${i.hour!=14}"><option value="14">14</option></c:if>
		<c:if test="${i.hour==15}"><option value="15" selected>15</option></c:if><c:if test="${i.hour!=15}"><option value="15">15</option></c:if>
		<c:if test="${i.hour==16}"><option value="16" selected>16</option></c:if><c:if test="${i.hour!=16}"><option value="16">16</option></c:if>
		<c:if test="${i.hour==17}"><option value="17" selected>17</option></c:if><c:if test="${i.hour!=17}"><option value="17">17</option></c:if>
		<c:if test="${i.hour==18}"><option value="18" selected>18</option></c:if><c:if test="${i.hour!=18}"><option value="18">18</option></c:if>
		<c:if test="${i.hour==19}"><option value="19" selected>19</option></c:if><c:if test="${i.hour!=19}"><option value="19">19</option></c:if>
		<c:if test="${i.hour==20}"><option value="20" selected>20</option></c:if><c:if test="${i.hour!=20}"><option value="20">20</option></c:if>
		<c:if test="${i.hour==21}"><option value="21" selected>21</option></c:if><c:if test="${i.hour!=21}"><option value="21">21</option></c:if>
		<c:if test="${i.hour==22}"><option value="22" selected>22</option></c:if><c:if test="${i.hour!=22}"><option value="22">22</option></c:if>
		<c:if test="${i.hour==23}"><option value="23" selected>23</option></c:if><c:if test="${i.hour!=23}"><option value="23">23</option></c:if>
	</select>
	</td>
	<td><select name="Minute${i.arrayPlace}">
		<c:if test="${i.minute==0}"><option value="0" selected>0</option></c:if><c:if test="${i.minute!=0}"><option value="0">0</option></c:if>
		<c:if test="${i.minute==15}"><option value="15" selected>15</option></c:if><c:if test="${i.minute!=15}"><option value="15">15</option></c:if>
		<c:if test="${i.minute==30}"><option value="30" selected>30</option></c:if><c:if test="${i.minute!=30}"><option value="30">30</option></c:if>
		<c:if test="${i.minute==45}"><option value="45" selected>45</option></c:if><c:if test="${i.minute!=45}"><option value="45">45</option></c:if>
	</select>
	</td>
</tr>
</c:forEach>
</table>
<input type="submit" name="add" value="Add">
<input type="submit" name="delete" value="Delete">
<input type="submit" name="commit" value="Commit Changes">
<input type="submit" name="cancel" value="Cancel">
</form>
</td>
</tr>
</table>

</body>
</html>