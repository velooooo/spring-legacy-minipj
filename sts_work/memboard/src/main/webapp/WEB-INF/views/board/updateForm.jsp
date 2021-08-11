<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateForm</title>
<h2>글 수정 폼</h2>
<form method="post" action="updatePro.do">
	<table>
		<tr>
			<td>이름</td>
			<td>
				<input type="text" name="writer" value="${bdto.writer}"/>
				<input type="hidden" name="num" value="${bdto.num}"/>
			</td>
		</tr>
		
		<tr>
			<td>글제목</td>
			<td>
				<input type="text" name="subject" value="${bdto.subject}" size="40"/>
			</td>
		</tr>
		
		<tr>
			<td>글내용</td>
			<td>
				<textarea name="content" rows="10" cols="60">${bdto.content}</textarea>
			</td>
		</tr>
		
		<tr>
			<td>암호</td>
			<td><input type="password" name="pw" size="10"/></td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="글수정"/>
				<input type="reset" value="다시쓰기"/>
				<input type="button" value="글 목록" onclick="location.href='list.do?pageNum=${pageNum}'"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>