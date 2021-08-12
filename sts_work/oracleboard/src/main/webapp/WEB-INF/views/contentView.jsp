<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세 보기(수정 가능한)</title>
</head>
<body>

<h2 class="tit">글 상세 보기(수정 가능한)</h2>
<form method="post" action="updatePro">
	<input type="hidden" name="bNum" value="${bdto.bNum}"/>
	<table>
		<tr>
			<td>번호</td>
			<td>${bdto.bNum}</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${bdto.bHit}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="bName" size="30" value="${bdto.bName}"/></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="bTitle" size="40" value="${bdto.bTitle}"/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea name="bContent" rows="10" cols="50">${bdto.bContent}</textarea>
			</td>
		</tr>
		<div class="btn-box clear">
			<input class="btn-item" type="submit" value="글쓰기"/>
			<a class="btn-item" href="list">글 목록</a>
			<a class="btn-item" href="deletePro?bNum=${bdto.bNum}">삭제</a>
			<a class="btn-item" href="replyForm?bNum=${bdto.bNum}">답글작성</a>
		</div>
	</table>
</form>

</body>
</html>