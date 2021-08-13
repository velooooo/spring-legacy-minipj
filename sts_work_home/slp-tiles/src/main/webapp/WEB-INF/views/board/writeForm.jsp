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
<c:if test="${no==0}"><!-- 글번호가 없으면 첫 번째 글이므로 일반 글쓰기 -->
	<h2>게시판 글쓰기</h2>
</c:if>
<c:if test="${no!=0}"><!-- 글번호가 있으면 첫 번째 글이 아니므로 답글쓰기 -->
	<h2>답글쓰기</h2>
</c:if>
<form method="post" action="writePro" onSubmit="return check()">
	<input type="hidden" name="pageNo" value="${pageNo}">
	<input type="hidden" name="no" value="${no}">
	<input type="hidden" name="re_group" value="${re_group}">
	<input type="hidden" name="re_step" value="${re_step}">
	<input type="hidden" name="re_depth" value="${re_depth}">
	
	<div class="form-box">
		<div class="form-block">
			<p><label for="writer">이름</label></p>
			<p><input type="text" name="writer" id="writer" size="30"/></p>
		</div>
		<div class="form-block">
			<p><label for="subject">글 제목</label></p>
			<p>
				<!-- 원글 -->
				<c:if test="${no==0}">
					<input type="text" name="subject" id="subject" size="40"/>
				</c:if>
				<!-- 답글 -->
				<c:if test="${no!=0}">
					<input type="text" name="subject" id="subject" size="40" value="[답변]"/>
				</c:if>
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
			<!-- 원글쓰기 -->
			<c:if test="${no==0}">
				<input class="btn-item" type="submit" value="글쓰기"/>
			</c:if>
			<!-- 답글쓰기 -->
			<c:if test="${no!=0}">
				<input class="btn-item" type="submit" value="답글쓰기"/>
			</c:if>
			
			<input class="btn-item" type="reset" value="다시쓰기"/>
			<input class="btn-item" type="button" value="글목록" onClick="location.href='basic-list'"/>
		</div>
	</div>
</form>
