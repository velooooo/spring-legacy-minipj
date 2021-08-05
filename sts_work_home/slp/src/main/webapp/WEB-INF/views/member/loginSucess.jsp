<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--loginSucess--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script>
function updateMember(){
	document.updateForm.action="memUpdateForm";//회원정보수정
	document.updateForm.submit();//서버로 전송
}
function deleteMember(){
	document.delForm.action="deleteMember";//회원탈퇴
	document.delForm.submit();//서버로 전송
}
</script>
<h2>${mdto.name}님 저희 홈에 오신것을 환영합니다.</h2>
<%--session등록--%>
<c:set var="id" value="${mdto.id}" scope="session"/>
<a href="list">게시판 글 목록</a>
<a href="javaScript:updateMember()">내정보 수정</a>
<a href="javaScript:deleteMember()">회원탈퇴</a>
<a href="logOut">로그아웃</a>

<form name="updateForm" method="post">
	<input type="hidden" name="id" value="${id}"/>
</form>
<form name="delForm" method="post">
	<input type="hidden" name="id" value="${id}"/>
</form>

</body>
</html>