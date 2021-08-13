<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0"/>
<title>글내용 보기</title>
<link href="resources/css/common.css" rel="stylesheet">
</head>
<body>
<div class="wrapper">
	<h2 class="tit">글내용 보기</h2>
	<div class="content-box">
		<div class="content-block">
			<td>글번호</td>
			<td>${bdto.no}</td>
		</div>
		<div class="content-block">
			<td>조회수</td>
			<td>${bdto.hit}</td>
		</div>
		<div class="content-block">
			<td>이름</td>
			<td>${bdto.writer}</td>
		</div>
		<div class="content-block">
			<td>작성일</td>
			<td><fmt:formatDate value="${bdto.writedate}" pattern="yyyy-MM-dd"/></td>
		</div>
		<div class="content-block">
			<td>글제목</td>
			<td colspan="3">${bdto.title}</td>
		</div>
		<div class="content-block">
			<td>내용</td>
			<td colspan="3"><pre>${bdto.content}</pre></td>
		</div>
		<div class="btn-box">
			<input class="btn-item" type="button" value="글수정" onClick="location.href='updateForm?no=${no}&pageNo=${pageNo}'"/>
			<input class="btn-item" type="button" value="글삭제" onClick="location.href='delete?no=${no}&pageNo=${pageNo}'"/>
			<input class="btn-item" type="button" value="새글쓰기" onClick="location.href='writeForm'"/>
			<input class="btn-item" type="button" value="글목록" onClick="location.href='list?pageNo=${pageNo}'"/>
		</div>
	</div>
</div>
</body>
</html>