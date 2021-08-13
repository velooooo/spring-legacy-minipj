<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0"/>
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
<link href="resources/css/common.css" rel="stylesheet">
</head>
<body>
<div class="wrapper">
	<h2 class="tit">게시판 글 작성</h2>
	<form method="post" action="writePro" onSubmit="return check()">
		<input type="hidden" name="pageNo" value="${pageNo}">
		<input type="hidden" name="no" value="${no}">
		
		<div class="form-box">
			<div class="form-block">
				<td>이름</td>
				<td><input type="text" name="writer" id="writer" size="30"/></td>
			</div>
			<div class="form-block">
				<td>글 제목</td>
				<td>
					<input type="text" name="title" id="title" size="50"/>
				</td>
			</div>
			<div class="form-block">
				<td>글 내용</td>
				<td><textarea name="content" id="content" rows="10" cols="60"></textarea></td>
			</div>
			<div class="btn-box">
				<input class="btn-item" type="submit" value="글쓰기"/>
				<input class="btn-item" type="reset" value="다시쓰기"/>
				<input class="btn-item" type="button" value="글목록" onClick="location.href='list'"/>
			</div>
		</div>
	</form>
</div>
<script>
function check(){
	if($('#writer').val()==''){
		alert("이름을 입력하세요.");
		$('#writer').focus();
		return false;
	}
	if($('#title').val()==''){
		alert("제목을 입력하세요.");
		$('#title').focus();
		return false;
	}
	if($('#content').val()==''){
		alert("내용을 입력하세요.");
		$('#content').focus();
		return false;
	}
	return true
}
</script>
</body>
</html>