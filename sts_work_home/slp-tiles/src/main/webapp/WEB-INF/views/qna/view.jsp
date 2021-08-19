<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="btn-box clear">
	<input class="btn-item" type="button" value="글삭제" onClick="location.href='/qna/del?no=${no}&pageNo=${pageNo}'"/>
	<input class="btn-item" type="button" value="글수정" onClick="location.href='/qna/edit?no=${no}&pageNo=${pageNo}'"/>
	<input class="btn-item" type="button" value="글목록" onClick="location.href='/qna/list?pageNo=${pageNo}'"/>
	<input class="btn-item" type="button" value="새글쓰기" onClick="location.href='/qna/write'"/>
</div>
<article>
	<h2 class="tit tit-qna">${qdto.subject}</h2>
	<p class="con-info">
	<span>조회수 : ${qdto.hit}</span>
	<span>작성자 : ${qdto.writer}</span>
	<span>작성일 : <fmt:formatDate value="${qdto.wdate}" pattern="yyyy-MM-dd"/></span>
	</p>
</article>
<article>
	<p class="con-block">
	<pre>${qdto.content}</pre>
	</p>
	<!-- 댓글(답글)시작 -->
	<div class="comment-list">
		<h2 class="tit">댓글 전체 ${countCo}개</h2>
		<ul class="comment-box">
			<li class="comment-block">
				<form method="post" action="/qna/saveCoPro" onSubmit="return checkCo()">
					<input type="hidden" name="pageCoNo" value="${pageCoNo}">
					<input type="hidden" name="no" value="${no}">
					<input type="hidden" name="co_is" value="1">
					
					<div class="form-box">
						<div class="form-block">
							<p><input type="text" name="writer" id="writer" size="30" placeholder="작성자"/></p>
							<p><textarea name="content" id="content" placeholder="내용"></textarea></p>
						</div>
						<div class="form-block">
							<p><input type="password" name="pw" id="pw" size="20" placeholder="비밀번호"/></p>
						</div>
						<div class="btn-box clear">
							<input class="btn-item" type="submit" value="저장"/>
						</div>
					</div>
				</form>
			</li>
			<!-- 댓글이 있으면 -->
			<c:if test="${countCo!=0}">
			<!-- for시작 -->
			<c:forEach var="cdto" items="${listCo}">
				<li class="comment-block">
					<p>${cdto.content}</p>
					<p>작성자 : ${cdto.writer}</p>
					<div class="btn-box clear">
						<input class="btn-item" type="button" value="댓글삭제" onClick="location.href='/qna/delCo?no=${cdto.no}&pageCoNo=${pageCoNo}'"/>
						<input class="btn-item" type="button" value="댓글수정" onClick="location.href='/qna/editCo?no=${cdto.no}&pageCoNo=${pageCoNo}'"/>
						<input class="btn-item" type="button" value="대댓글쓰기" onClick="location.href='/qna/write?no=${cdto.no}&pageCoNo=${pageCoNo}&co_group=${cdto.co_group}&co_step=${bdto.co_step}&co_depth=${bdto.co_depth}'"/>
					</div>
				</li>
			</c:forEach>
			<!-- for끝 -->
			</c:if>
			<!-- 댓글이 없으면 -->
			<c:if test="${countCo==0}">
				<li class="comment-block">
				저장된 댓글이 없습니다.
				</li>
			</c:if>
		</ul>
	</div>
	<!-- 댓글(답글)끝 -->
</article>
<div class="btn-box clear">
	<input class="btn-item" type="button" value="글삭제" onClick="location.href='/qna/del?no=${no}&pageNo=${pageNo}'"/>
	<input class="btn-item" type="button" value="글수정" onClick="location.href='/qna/edit?no=${no}&pageNo=${pageNo}'"/>
	<input class="btn-item" type="button" value="글목록" onClick="location.href='/qna/list?pageNo=${pageNo}'"/>
	<input class="btn-item" type="button" value="새글쓰기" onClick="location.href='/qna/write'"/>
</div>
<script>
function checkCo(){
	if($('#writer').val()==''){
		alert("이름을 입력하세요.");
		$('#writer').focus();
		return false;
	}
	if($('#content').val()==''){
		alert("내용을 입력하세요.");
		$('#content').focus();
		return false;
	}
	if($('#pw').val()==''){
		alert("비밀번호를 입력하세요.");
		$('#pw').focus();
		return false;
	}
	return true
}
</script>