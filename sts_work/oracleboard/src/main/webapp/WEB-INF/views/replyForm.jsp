<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2 class="tit">답글쓰기 폼</h2>
<form method="post" action="replyPro">
	<input type="hidden" name="bNum" value="${bdto.bNum}"/>
	<input type="hidden" name="bGroup" value="${bdto.bGroup}"/>
	<input type="hidden" name="bStep" value="${bdto.bStep}"/>
	<input type="hidden" name="bIndent" value="${bdto.bIndent}"/>
	<div class="form-box">
		<div class="form-block">
			<p>글번호</p>
			<p>${bdto.bNum}</p>
		</div>
		<div class="form-block">
			<p>조회수</p>
			<p>${bdto.bHit}</p>
		</div>
		<div class="form-block">
			<p>글쓴이</p>
			<p><input type="text" name="bName" id="bName" size="30" maxlength="30" value="${bdto.bName}"/></p>
		</div>
		<div class="form-block">
			<p>글제목</p>
			<p><input type="text" name="bTitle" id="bTitle" size="40" maxlength="40" value="${bdto.bTitle}"/></p>
		</div>
		<div class="form-block">
			<p>글내용</p>
			<p>
				<textarea name="bContent" rows="10" cols="50">${bdto.bContent}</textarea>
			</p>
		</div>
	
		<div class="btn-box clear">
			<input class="btn-item" type="submit" value="답글저장"/>
			<a class="btn-item" href="list">글 목록</a>
		</div>
	</div>
</form>
</body>
</html>