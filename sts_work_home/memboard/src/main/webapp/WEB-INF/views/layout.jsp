<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>layout</title>
</head>
<body>
<table board="1" style="width:100%;min-width:60%;">
	<tr>
		<td colspan="2" height="50" bgcolor="cyan">
			<tiles:insertAttribute name="header"></tiles:insertAttribute>
		</td>
	</tr>
	<tr>
		<td width="70" height="50" ><tiles:insertAttribute name="side"></tiles:insertAttribute></td>
		<td><tiles:insertAttribute name="content"></tiles:insertAttribute></td>
	</tr>
	<tr>
		<td colspan="2" height="100"><tiles:insertAttribute name="footer"></tiles:insertAttribute></td>
	</tr>
</table>
</body>
</html>