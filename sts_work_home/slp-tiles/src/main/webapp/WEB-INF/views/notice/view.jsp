<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="btn-box clear">
	<input class="btn-item" type="button" value="글삭제" onClick="location.href='/notice/del?no=${no}&pageNo=${pageNo}'"/>
	<input class="btn-item" type="button" value="글수정" onClick="location.href='/notice/edit?no=${no}&pageNo=${pageNo}'"/>
	<input class="btn-item" type="button" value="글목록" onClick="location.href='/notice/list?pageNo=${pageNo}'"/>
	<input class="btn-item" type="button" value="새글쓰기" onClick="location.href='/notice/write'"/>
</div>
<article>
	<h2 class="tit tit-notice">${ndto.subject}</h2>
	<p class="con-info">
	<span>조회수 : ${ndto.hit}</span>
	<span>작성자 : ${ndto.writer}</span>
	<span>작성일 : <fmt:formatDate value="${ndto.wdate}" pattern="yyyy-MM-dd"/></span>
	</p>
</article>
<article>
	<p class="con-block">
	<pre>${ndto.content}</pre>
	</p>
</article>
<div class="btn-box clear">
	<input class="btn-item" type="button" value="글삭제" onClick="location.href='/notice/del?no=${no}&pageNo=${pageNo}'"/>
	<input class="btn-item" type="button" value="글수정" onClick="location.href='/notice/edit?no=${no}&pageNo=${pageNo}'"/>
	<input class="btn-item" type="button" value="글목록" onClick="location.href='/notice/list?pageNo=${pageNo}'"/>
	<input class="btn-item" type="button" value="새글쓰기" onClick="location.href='/notice/write'"/>
</div>
