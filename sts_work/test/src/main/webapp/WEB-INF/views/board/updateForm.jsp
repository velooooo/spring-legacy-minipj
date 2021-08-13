<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0"/>
<title>게시판 글 수정</title>
<link href="resources/css/common.css" rel="stylesheet">
</head>
<body>
<div class="wrapper">
	<h2 class="tit">게시판 글 수정</h2>
	<form method="post" action="updatePro" onSubmit="return check()">
		<input type="hidden" name="no" value="${bdto.no}"/>
		<div class="form-box">
			<div class="form-block">
				<td>이름</td>
				<td><input type="text" name="writer" id="writer" value="${bdto.writer}" size="30"/></td>
			</div>
			<div class="form-block">
				<td>글 제목</td>
				<td>
					<input type="text" name="title" id="title" value="${bdto.title}" size="50"/>
				</td>
			</div>
			<div class="form-block">
				<td>글 내용</td>
				<td><textarea name="content" id="content" rows="10" cols="60">${bdto.content}</textarea></td>
			</div>
			<div class="btn-box">
				<input class="btn-item" type="submit" value="글수정"/>
				<input class="btn-item" type="reset" value="다시쓰기"/>
				<input class="btn-item" type="button" value="글목록" onClick="location.href='list?pageNo=${pageNo}'"/>
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