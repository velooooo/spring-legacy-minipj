<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<script>
	function check() {
		//데이터 유효성 체크
		if ($('#id').val() == '') {
			alert("id를 입력 하시오");
			$('#id').focus();
			return false;
		}

		if ($('#pw').val() == '') {
			alert("암호를 입력 하시오");
			$('#pw').focus();
			return false;
		}

		if ($('#pw2').val() == '') {
			alert("암호확인를 입력 하시오");
			$('#pw2').focus();
			return false;
		}

		//암호와 암호확인이 같은 비교
		if ($('#pw').val() != $('#pw2').val()) {
			alert("암호와 암호확인이 다릅니다");
			$('#pw').val('');//내용삭제
			$('#pw2').val('');
			$('#pw').focus();
			return false;
		}

		//이름
		if ($("#name").val() == '') {
			alert("이름을 입력 하세요 ");
			$("#name").focus();
			return false;
		}
		return true;
	}//function-end

	//Ajax
	function confirmIDCheck() {
		if ($('#id').val() == '') {
			alert("ID를 입력 하시요");
		} else {
			//ID가 입력 되었을때 
			$.ajax({
				type : "POST",
				url : "confirmID",
				data : "id=" + $('#id').val(),//서버로 넘길 인수값
				dataType : 'JSON',//서버가 보내준 자료 타입
				success : function(data) {
					if (data.check == 1) {
						//사용가능한 ID
						alert("사용가능한 ID");
						$('#pw').focus();

					} else if (data.check == -1) {
						//사용중인 ID
						alert("사용중인 ID");
						$('#id').val('').focus();
					}
				}//success-end

			});
		}//else-end
	}//cinfirmIDCheck()-end
</script>

<style type="text/css">
h2 {
	text-align: center;
}

table {
	margin: auto;
	background-color: ivoy;
}
</style>
</head>
<body>
	<h2>회원가입</h2>
	<form method="post" action="insertPro" onSubmit="return check()">

		<table border="1">

			<tr>
				<td>ID</td>
				<td><input type="text" name="id" id="id" size="20"> <input
					type="button" value="ID중복체크" onclick="confirmIDCheck()"></td>
			</tr>

			<tr>
				<td>암호</td>
				<td><input type="password" name="pw" id="pw" size="10"></td>
			</tr>

			<tr>
				<td>암호확인</td>
				<td><input type="password" name="pw2" id="pw2" size="10"></td>
			</tr>

			<tr>
				<td>이름</td>
				<td><input type="text" name="name" id="name" size="30"></td>
			</tr>

			<tr>
				<td>이메일</td>
				<td><input type="text" name="email1" id="email1">@ <select
					name="email2" id="email2">
						<option value="@naver.com">naver.com</option>
						<option value="@daum.net">daum.net</option>
						<option value="@nate.com">nate.com</option>
				</select></td>
			</tr>

			<!-- 전화번호 -->
			<tr>
				<td>전화번호</td>
				<td><select name="tel1" id="tel1">
						<option value="010">010</option>
						<option value="017">017</option>
						<option value="018">018</option>
				</select> <input type="text" name="tel2" id="tel2" size="4"> <input
					type="text" name="tel3" id="tel3" size="4"></td>
			</tr>

			<!-- 우편번호 -->
			<tr>
				<td>우편번호</td>
				<td><input type="text" name="zipcode" id="zipcode" size="7"
					readonly> <input type="button" value="주소검색"
					onClick="openDaumPostcode()"></td>
			</tr>

			<!-- 주소 -->
			<tr>
				<td>주소</td>
				<td><input type="text" name='addr' id="addr" size="60" readonly>
					<br> 상세주소:<input type="text" name="addr2" id="addr2" size="40">
				</td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="회원가입"> <input
					type="reset" value="다시입력"> <input type="button"
					value="가입안함" onClick="location='main'"></td>
			</tr>
		</table>
	</form>
</body>
</html>