<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
function check(){
	if($('#writer').val()==''){
		alert("이름을 입력하세요.");
		$('#writer').focus();
		return false;
	}
	if($('#subject').val()==''){
		alert("제목을 입력하세요.");
		$('#subject').focus();
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
<h2 class="tit">QnA 글쓰기</h2>
<form method="post" action="/qna/savePro" onSubmit="return check()">
	<input type="hidden" name="pageNo" value="${pageNo}">
	<input type="hidden" name="no" value="${no}">
	<div class="form-box">
		<div class="form-block">
			<p><label for="writer">이름</label></p>
			<p><input type="text" name="writer" id="writer" size="30"/></p>
		</div>
		<div class="form-block">
			<p><label for="subject">글 제목</label></p>
			<p>
				<input type="text" name="subject" id="subject" size="40"/>
			</p>
		</div>
		<div class="form-block">
			<p><label for="content">글 내용</label></p>
			<p><textarea name="content" id="content" rows="10" cols="60"></textarea></p>
		</div>
		<div class="form-block">
			<p><label for="pw">비밀번호</label></p>
			<p><input type="password" name="pw" id="pw" size="20"/></p>
		</div>
		<div class="btn-box clear">
			<input class="btn-item" type="submit" value="글쓰기"/>
			
			<input class="btn-item" type="reset" value="다시쓰기"/>
			<input class="btn-item" type="button" value="글목록" onClick="location.href='/qna/list'"/>
		</div>
	</div>
</form>
