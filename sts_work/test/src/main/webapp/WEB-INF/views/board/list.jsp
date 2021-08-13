<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0"/>
<title>게시판 목록</title>
<link href="resources/css/common.css" rel="stylesheet">
</head>
<body>
<div class="wrapper">
	<h2 class="tit">목록</h2>
	<div class="btn-box">
		<a class="btn-item" href="writeForm">글작성하기</a>
	</div>
	<table>
		<thead>
			<tr>
				<td>글번호</td>
				<td>제목</td>
				<td>글쓴이</td>
				<td>작성일</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="dto" items="${list}">
			<tr>
				<td>${dto.no}</td>
				<td><a href="content?no=${dto.no}&pageNo=${pageNo}" title="${dto.title}"/>${dto.title}</a></td>
				<td>${dto.writer}</td>
				<td><fmt:formatDate value="${dto.writedate}" pattern="yyyy-MM-dd"/></td>
				<td>${dto.hit}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 블럭, 페이징 처리 -->
	<div class="paging-box">
		<div class="paging-block">
			<!-- 에러 방지 -->
			<!-- 페이지수가 10,20,30보다 크면? -->
			<c:if test="${endPage>pageCount}">
				<c:set var="endPage" value="#{pageCount}"/>
			</c:if>
			
			<!-- 이전 블럭 -->
			<c:if test="${startPage>10}">
				<a class="paging-item" href="list?pageNo=${startPage-10}">이전블럭</a>
			</c:if>
			
			<!-- 페이지처리 -->
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a class="paging-item" href="list?pageNo=${i}">[${i}]</a>
			</c:forEach>
			
			<!-- 다음 블럭 -->
			<c:if test="${endPage<pageCount}">
				<a class="paging-item" href="list?pageNo=${startPage+10}">다음블럭</a>
			</c:if>
		</div>
	</div>
</div>
</body>
</html>