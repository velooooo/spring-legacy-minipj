<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <jsp:include page="../inc/head.jsp"/> --%>
<h2 class="tit">Notice 게시판 목록(전체 ${count}글)</h2>
<div class="btn-box clear">
	<a class="btn-item" href="/notice/write">글쓰기</a>
</div>
<table>
	<thead>
		<tr>
			<td>글번호</td>
			<td>제목</td>
			<td>글쓴이</td>
			<td>작성일</td>
			<td>조회수</td>
			<td>ip</td>
		</tr>
	</thead>
	<!-- 글이 있으면 -->
	<c:if test="${count!=0}">
	<tbody>
		<c:forEach var="ndto" items="${list}">
		<tr>
			<td>
				<!-- ndto.no 수정할것 -->
				<%-- <c:out value="${number}"/>
				<c:set var="number" value="${number-1}"/> --%>
				<!-- 역순을 위해 -1을 해줬는데 왜 역순으로 해야하는지 모르겠음. -->
				${ndto.no}
			</td>
			<!-- 글제목 -->
			<td class="bo_tit">
				<!-- 글제목을 클릭하면 글내용보기로 이동 -->
				<a href="/notice/view?no=${ndto.no}&pageNo=${pageNo}" title="${ndto.subject}">${ndto.subject}</a>
			</td>
			<td>${ndto.writer}</td>
			<td><fmt:formatDate value="${ndto.wdate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
			<td>${ndto.hit}</td>
			<td>${ndto.ip}</td>
		</tr>
		</c:forEach>
	</tbody>
	</c:if>
	<!-- 글이 없으면 -->
	<c:if test="${count==0}">
	<tbody>
		<tr>
			<td colspan="6">
			저장된 글이 없습니다.
			</td>
		</tr>
	</tbody>
	</c:if>
</table>
	
<!-- 블럭, 페이징 처리 -->
<table>
	<tr>
		<td align="center">
			<!-- 에러 방지 -->
			<!-- 페이지수가 10,20,30보다 크면? -->
			<c:if test="${endPage>pageCount}">
				<c:set var="endPage" value="#{pageCount}"/>
			</c:if>
			
			<!-- 이전 블럭 -->
			<c:if test="${startPage>10}">
				<a href="/notice/list?pageNo=${startPage-10}">이전블럭</a>
			</c:if>
			
			<!-- 페이지처리 -->
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a href="/notice/list?pageNo=${i}">[${i}]</a>
			</c:forEach>
			
			<!-- 다음 블럭 -->
			<c:if test="${endPage<pageCount}">
				<a href="/notice/list?pageNo=${startPage+10}">다음블럭</a>
			</c:if>
		</td>
	</tr>
</table>
