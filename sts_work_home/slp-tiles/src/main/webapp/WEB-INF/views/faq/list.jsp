<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2 class="tit">자주 묻는 질문(전체 ${count}글)</h2>
<div class="btn-box clear">
	<a class="btn-item" href="/faq/write">글쓰기</a>
</div>
<c:if test="${count!=0}">
	<article>
		<c:forEach var="dto" items="${list}">
		<button class="accordion clear">${dto.no} - ${dto.subject}<span class="faq-date">작성일 : ${dto.wdate}</span></button>
		<div class="accordion-panel">
			<div>${dto.content}</div>
			<div class="btn-box clear">
				<input class="btn-item" type="button" value="글삭제" onClick="location.href='/faq/del?no=${dto.no}&pageNo=${pageNo}'"/>
				<input class="btn-item" type="button" value="글수정" onClick="location.href='/faq/edit?no=${dto.no}&pageNo=${pageNo}'"/>
			</div>
			</div>
		</c:forEach>
	</article>
</c:if>
<!-- 글이 없으면 -->
<c:if test="${count==0}">
	<article>저장된 글이 없습니다.</article>
</c:if>
