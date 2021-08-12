<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2 class="tit">글작성</h2>
<form method="post" action="writePro">
	<table>
		<tr>
			<td>이름</td>			
			<td><input type="text" name="bName" size="30" maxlength="10"/></td>			
		</tr>
		<tr>
			<td>제목</td>			
			<td><input type="text" name="bTitle" size="40" maxlength="40"/></td>			
		</tr>
		<tr>
			<td>내용</td>			
			<td>
				<textarea name="bContent" rows="10" cols="50"></textarea>
			</td>
		</tr>
		<div class="btn-box clear">
			<input class="btn-item" type="submit" value="글쓰기"/>
			<input class="btn-item" type="reset" value="다시쓰기"/>
			<a class="btn-item" href="list">글 목록</a>
		</div>
	</table>
</form>
</body>
</html>