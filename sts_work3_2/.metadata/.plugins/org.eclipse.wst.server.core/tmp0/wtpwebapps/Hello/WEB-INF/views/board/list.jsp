<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
</head>
<body>
<h2>글목록(전체)</h2>
<table width="80%">
	<tr>
		<td align="right">
			<a href="writeForm.do">글쓰기</a>
		</td>
	</tr>
</table>
<!-- 글이 없으면 -->
<c:if test="${count==0}">
	<table width="80%">
		<tr>
			<td>글번호</td>
			<td>제목</td>
			<td>글쓴이</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
		<c:forEach var="dto" items="${list}">
		<tr>
			<td>
				<c:out value="${number}"/>
				<c:set var="number" value="${number-1}"/>
				<!-- 역순을 위해 -1을 해줬는데 왜 역순으로 해야하는지 모르겠음. -->
			</td>
			<!-- 글제목 -->
			<td>
			<!-- 답글이면 -->
				<!-- 답글이면 이미지 넣는 방법 webapp/resources/imgs-->
				<c:if test="${dto.re_level>0}"><!-- re_level이 0보다 크면 -->
					<img src="resources/imgs/level.gif" width="${5*dto.re_level}" height="16" /><!-- 레벨만큼 들어가게 -->
					<img src="resources/imgs/re.gif"/><!-- 답글표시 이미지 -->
				</c:if>
			</td>
			<td>글쓴이</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
		</c:forEach>
	</table>
</c:if>
<!-- 글이 있으면 -->
<c:if test="${count!=0}">
저장된 글이 없습니다.
</c:if>
</body>
</html>