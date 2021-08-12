<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
</head>
<body>
<h2 class="tit">글 목록</h2>
<div class="btn-box clear">
	<a class="btn-item" href="writeForm">글쓰기</a>
</div>
<table>
	<thead>
		<tr>
			<td>번호</td>
			<td>이름</td>
			<td>제목</td>
			<td>날짜</td>
			<td>조회수</td>
		</tr>
	</thead>
	<tbody>
		<!-- 리스트 시작 -->
		<c:forEach var="dto" items="${list}">
		<tr>
			<td>${dto.bNum}</td>			
			<td>${dto.bName}</td>
			<!-- 글 제목 시작 -->
			<td>
				<!-- 답글시 들여쓰기 시작 -->
				<c:forEach begin="1" end="${dto.bIndent}">
				-
				</c:forEach>
				<!-- 답글시 들여쓰기 끝 -->
				<a href="contentView?bNum=${dto.bNum}" title="${dto.bTitle} 보러가기">
					${dto.bTitle}
				</a>
			</td>
			<!-- 글 제목 끝 -->
			<td><fmt:formatDate value="${dto.bDate}" pattern="yyyy-MM-dd"/></td>			
			<td>${dto.bHit}</td>			
		</tr>
		</c:forEach>
		<!-- 리스트 끝 -->
	</tbody>
</table>
</body>
</html>