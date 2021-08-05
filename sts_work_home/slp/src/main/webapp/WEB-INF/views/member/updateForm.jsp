<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- updateForm.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	function openDaumPostcode() {
		new daum.Postcode({
			oncomplete : function(data) {
				document.getElementById('zipcode').value = data.zonecode;
				document.getElementById('addr').value = data.address;
			}
		}).open();
	}//openDaumPostcode()---
</script>
<style>
h2 {
	text-align: center;
}

table {
	margin: auto;
	padding: 4px;
}
</style>
</head>
<body>
	<h2>내정보수정</h2>
	<form name="upForm" method="post" action="memUpdatePro">
		<table>
			<tr>
				<td>ID</td>
				<td><b>${mdto.id}</b> <input type="hidden" name="id" value="${mdto.id}" size="20"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pw" id="pw" value="${mdto.pw}" size="20"></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" name="pw2" id="pw2" value="${mdto.pw}" size="20" onblur="pwCheck()"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" id="name" size="30" value="${mdto.name}"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>
					<input type="text" name="email1" id="email1" value="${email1}">@
					<select name="email2" id="email2">
							<option value="${email2}">${email2}</option>
							<option value="@naver.com">naver.com</option>
							<option value="@daum.net">daum.net</option>
							<option value="@nate.com">nate.com</option>
					</select>
				</td>
			</tr>
			
			<!-- 전화번호 -->
			<tr>
				<td>전화번호</td>
				<td><select name="tel1" id="tel1">
						<option value="${tel1}">${tel1}</option>
						<option value="010">010</option>
						<option value="017">017</option>
						<option value="018">018</option>
				</select> <input type="text" name="tel2" id="tel2" size="4" value="tel2"> <input
					type="text" name="tel3" id="tel3" size="4" value="tel3"></td>
			</tr>

			<!-- 우편번호 -->
			<tr>
				<td>우편번호</td>
				<td><input type="text" name="zipcode" id="zipcode" size="7" value="${mdto.zipcode}"
					readonly> <input type="button" value="주소검색"
					onClick="openDaumPostcode()"></td>
			</tr>

			<!-- 주소 -->
			<tr>
				<td>주소</td>
				<td><input type="text" name='addr' id="addr" size="60" value="${mdto.addr}" readonly>
					<br> 상세주소:<input type="text" name="addr2" id="addr2" size="40" value="${mdto.addr2}">
				</td>
			</tr>

			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="내정보 수정"> 
					<input type="reset" value="다시입력">
					<input type="button" value="취소" onClick="location='main'"></td>
			</tr>
		</table>

	</form>
	
<script>

function pwCheck(){
	if(document.upForm.pw.value!=document.upForm.pw2.value){
		alert("비밀번호가 다릅니다.");
		document.upForm.pw.focus();
		return false;
	}
}

</script>
</body>
</html>