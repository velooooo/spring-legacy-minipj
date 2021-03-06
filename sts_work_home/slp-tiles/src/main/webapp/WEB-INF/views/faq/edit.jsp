<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2 class="tit">자주 묻는 질문 - ${fdto.subject} 수정</h2>
<form method="post" action="updatePro">
	<div class="form-box">
		<div class="form-block">
			<p><label for="writer">이름</label></p>
			<p>
				<input type="text" name="writer" id="writer" value="${fdto.writer}"/>
				<input type="hidden" name="no" value="${fdto.no}"/>
			</p>
		</div>
		
		<div class="form-block">
			<p><label for="subject">글제목</label></p>
			<p>
				<input type="text" name="subject" id="subject" value="${fdto.subject}" size="40"/>
			</p>
		</div>
		
		<div class="form-block">
			<p><label for="content">글내용</label></p>
			<div>
				<textarea name="content" id="content" rows="10" cols="60">${fdto.content}</textarea>
			</div>
		</div>
		
		<div class="btn-box clear">
			<input class="btn-item" type="submit" value="글수정"/>
			<input class="btn-item" type="reset" value="다시쓰기"/>
			<input class="btn-item" type="button" value="글 목록" onclick="location.href='/faq/list?pageNo=${pageNo}'"/>
		</div>
	</div>
</form>