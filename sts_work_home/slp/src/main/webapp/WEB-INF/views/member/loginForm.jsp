<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--loginForm--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<style type="text/css">
table {
	margin: auto;
	background-color: #fcfcfc;
}

h2 {
	text-align: center;
}
</style>
	<script>
		function check() {
			if (document.loginFrom.id.value == "") {
				alert('아이디를 입력하시오');
				document.loginForm.id.focus();
				return false;
			}
			if (document.loginFrom.pw.value == "") {
				alert('비밀번호를 입력하시오');
				document.loginForm.pw.focus();
				return false;
			}
			return true;
		}
	</script>

	<c:if test="${!mag.equals(null)}">
	${msg}
</c:if>

	<h2>로그인</h2>

	<form name="loginForm" method="post" action="loginPro"
		onsubmit="return check()">

		<table>
			<tr>
				<td>ID</td>
				<td><input type="text" name="id" id="id" size="20"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pw" id="pw" size="20"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="login">
					<input type="reset" value="다시입력">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>