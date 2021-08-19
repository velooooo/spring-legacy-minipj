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
		<ul class="comment-box">
			<li class="comment-block">
			<form method="post" action="/qna/saveCoPro" onSubmit="return check()">
				<input type="hidden" name="pageNo" value="${pageNo}">
				<input type="hidden" name="no" value="${no}">
				<input type="hidden" name="subject" value="comment">
				
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
			<!-- for시작 -->
			<li class="comment-block"></li>
			<!-- for끝 -->
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
