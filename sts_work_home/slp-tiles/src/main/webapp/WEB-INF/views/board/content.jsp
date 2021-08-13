<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="btn-box clear">
	<input class="btn-item" type="button" value="글수정" onClick="location.href='updateForm?no=${no}&pageNo=${pageNo}'"/>
	<input class="btn-item" type="button" value="글삭제" onClick="location.href='delete?no=${no}&pageNo=${pageNo}'"/>
	<input class="btn-item" type="button" value="글목록" onClick="location.href='basic-list?pageNo=${pageNo}'"/>
	<input class="btn-item" type="button" value="새글쓰기" onClick="location.href='writeForm'"/>
	<input class="btn-item" type="button" value="답글쓰기" onClick="location.href='writeForm?no=${no}&pageNo=${pageNo}&re_group=${bdto.re_group}&re_step=${bdto.re_step}&re_depth=${bdto.re_depth}'"/>
</div>
<article>
	<h2 class="tit tit-board">${bdto.subject}</h2>
	<p class="con-info">
	<span>조회수 : ${bdto.hit}</span>
	<span>작성자 : ${bdto.writer}</span>
	<span>작성일 : <fmt:formatDate value="${bdto.wdate}" pattern="yyyy-MM-dd"/></span>
	</p>
</article>
<article>
	<p class="con-block">
	<pre>${bdto.content}</pre>
	</p>
</article>
<div class="btn-box clear">
	<input class="btn-item" type="button" value="글수정" onClick="location.href='updateForm?no=${no}&pageNo=${pageNo}'"/>
	<input class="btn-item" type="button" value="글삭제" onClick="location.href='delete?no=${no}&pageNo=${pageNo}'"/>
	<input class="btn-item" type="button" value="글목록" onClick="location.href='basic-list?pageNo=${pageNo}'"/>
	<input class="btn-item" type="button" value="새글쓰기" onClick="location.href='writeForm'"/>
	<input class="btn-item" type="button" value="답글쓰기" onClick="location.href='writeForm?no=${no}&pageNo=${pageNo}&re_group=${bdto.re_group}&re_step=${bdto.re_step}&re_depth=${bdto.re_depth}'"/>
</div>
