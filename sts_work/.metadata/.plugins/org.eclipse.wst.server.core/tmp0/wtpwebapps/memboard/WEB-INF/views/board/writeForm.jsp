<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>writeForm</title>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
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
<style src="/css/common.css"></style>
</head>
<body>
<c:if test="${num==0}"><!-- 글번호가 없으면 첫 번째 글이므로 일반 글쓰기 -->
	<h2>게시판 글쓰기</h2>
</c:if>
<c:if test="${num!=0}"><!-- 글번호가 있으면 첫 번째 글이 아니므로 답글쓰기 -->
	<h2>답글쓰기</h2>
</c:if>
<form method="post" action="writePro.do" onSubmit="return check()">
	<input type="hidden" name="pageNum" value="${pageNum}">
	<input type="hidden" name="num" value="${num}">
	<input type="hidden" name="ref" value="${ref}">
	<input type="hidden" name="re_step" value="${re_step}">
	<input type="hidden" name="re_level" value="${re_level}">
	
	<table>
		<tr>
			<td>이름</td>
			<td><input type="text" name="writer" id="writer" size="30"/></td>
		</tr>
		<tr>
			<td>글 제목</td>
			<td>
				<!-- 원글 -->
				<c:if test="${num==0}">
					<input type="text" name="subject" id="subject" size="40"/>
				</c:if>
				<!-- 답글 -->
				<c:if test="${num!=0}">
					<input type="text" name="subject" id="subject" size="40" value="[답변]"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td>글 내용</td>
			<td><textarea name="content" id="content" rows="10" cols="60"></textarea></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="pw" id="pw" size="20"/></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<!-- 원글쓰기 -->
				<c:if test="${num==0}">
					<input type="submit" value="글쓰기"/>
				</c:if>
				<!-- 답글쓰기 -->
				<c:if test="${num!=0}">
					<input type="submit" value="답글쓰기"/>
				</c:if>
				
				<input type="reset" value="다시쓰기"/>
				<input type="button" value="글목록" onClick="location.href='list.do'"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>