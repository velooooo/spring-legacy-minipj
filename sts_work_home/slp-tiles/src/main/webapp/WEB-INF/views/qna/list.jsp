<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2 class="tit">QnA (전체 ${count}글)</h2>
<div class="btn-box clear">
	<a class="btn-item" href="/qna/write">글쓰기</a>
</div>
<c:if test="${count!=0}">
	<article>
		<c:forEach var="qdto" items="${list}">
		<button class="accordion clear">${qdto.no} - ${qdto.subject}<span class="qna-date">작성일 : ${qdto.wdate}</span></button>
		<div class="accordion-panel">
			<div>${qdto.content}</div>
			<div class="btn-box clear">
				<input class="btn-item" type="button" value="글삭제" onClick="location.href='/qna/del?no=${qdto.no}&pageNo=${pageNo}'"/>
				<input class="btn-item" type="button" value="글수정" onClick="location.href='/qna/edit?no=${qdto.no}&pageNo=${pageNo}'"/>
			</div>
			</div>
		</c:forEach>
	</article>
</c:if>
<!-- 글이 없으면 -->
<c:if test="${count==0}">
	<article>저장된 글이 없습니다.</article>
</c:if>
