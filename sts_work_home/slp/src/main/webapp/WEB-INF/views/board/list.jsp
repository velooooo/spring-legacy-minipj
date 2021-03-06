<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <jsp:include page="../inc/head.jsp"/> --%>
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
			<a href="writeForm">글쓰기</a>
		</td>
	</tr>
</table>
<!-- 글이 있으면 -->
<c:if test="${count!=0}">
	<table width="80%">
		<tr>
			<td>글번호</td>
			<td>제목</td>
			<td>글쓴이</td>
			<td>작성일</td>
			<td>조회수</td>
			<td>ip</td>
		</tr>
		<c:forEach var="dto" items="${list}">
		<tr>
			<td>
				<!-- dto.num 수정할것 -->
				<c:out value="${number}"/>
				<c:set var="number" value="${number-1}"/>
				<!-- 역순을 위해 -1을 해줬는데 왜 역순으로 해야하는지 모르겠음. -->
			</td>
			<!-- 글제목 -->
			<td>
				<!-- 답글이면 -->
				<!-- 답글이면 이미지 넣는 방법 webapp/resources/imgs-->
				<c:if test="${dto.re_level>0}"><!-- re_level이 0보다 크면 -->
					<img src="resources/imgs/level.gif" width="${5*dto.re_level}" height="16" class="ico_level"/><!-- 레벨만큼 들어가게 -->
					<img src="resources/imgs/re.gif" class="ico_re"/><!-- 답글표시 이미지 -->
				</c:if>
			
				<!-- 원글이면 -->
				<c:if test="${dto.re_level==0}">
					<img src="resources/imgs/level.gif" width="${5*dto.re_level}" height="16" class="ico_level"/>
				</c:if>
			
				<!-- 글제목을 클릭하면 글내용보기로 이동 -->
				<a href="content?num=${dto.num}&pageNum=${pageNum}" title="${dto.subject}">${dto.subject}</a>
				
				<!-- 조회수가 20회 이상이면 hot.gif표시 -->
				<c:if test="${dto.readcount>=20}">
					<img src="resources/imgs/hot.gif" class="ico_hot"/>
				</c:if>
			</td>
			<td>${dto.writer}</td>
			<td><fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
			<td>${dto.readcount}</td>
			<td>${dto.ip}</td>
		</tr>
		</c:forEach>
	</table>
</c:if>
<!-- 글이 없으면 -->
<c:if test="${count==0}">
저장된 글이 없습니다.
</c:if>
<!-- 블럭, 페이징 처리 -->
<table width="80%">
	<tr>
		<td align="center">
			<!-- 에러 방지 -->
			<!-- 페이지수가 10,20,30보다 크면? -->
			<c:if test="${endPage>pageCount}">
				<c:set var="endPage" value="#{pageCount}"/>
			</c:if>
			
			<!-- 이전 블럭 -->
			<c:if test="${startPage>10}">
				<a href="list?pageNum=${startPage-10}">이전블럭</a>
			</c:if>
			
			<!-- 페이지처리 -->
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a href="list?pageNum=${i}">[${i}]</a>
			</c:forEach>
			
			<!-- 다음 블럭 -->
			<c:if test="${endPage<pageCount}">
				<a href="list?pageNum=${startPage+10}">다음블럭</a>
			</c:if>
		</td>
	</tr>
</table>
</body>
</html>