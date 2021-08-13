<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>글 수정 폼</h2>
<form method="post" action="updatePro">
	<div class="form-box">
		<div class="form-block">
			<p><label for="writer">이름</label></p>
			<p>
				<input type="text" name="writer" id="writer" value="${bdto.writer}"/>
				<input type="hidden" name="no" value="${bdto.no}"/>
			</p>
		</div>
		
		<div class="form-block">
			<p><label for="subject">글제목</label></p>
			<p>
				<input type="text" name="subject" id="subject" value="${bdto.subject}" size="40"/>
			</p>
		</div>
		
		<div class="form-block">
			<p><label for="content">글내용</label></p>
			<div>
				<textarea name="content" id="content" rows="10" cols="60">${bdto.content}</textarea>
			</div>
		</div>
		
		<div class="form-block">
			<p><label for="pw">암호</label></p>
			<p><input type="password" name="pw" id="pw" size="10"/></p>
		</div>
		
		<div class="btn-box clear">
			<input class="btn-item" type="submit" value="글수정"/>
			<input class="btn-item" type="reset" value="다시쓰기"/>
			<input class="btn-item" type="button" value="글 목록" onclick="location.href='basic-list?pageNo=${pageNo}'"/>
		</div>
	</div>
</form>
