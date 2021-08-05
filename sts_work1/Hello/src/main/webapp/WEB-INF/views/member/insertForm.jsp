<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%-- 회원가입 폼 / insertForm.jsp --%>
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
			alert("id를 입력하시오");
			$('#id').focus();
			return false;
		}

		if ($('#pw').val() == '') {
			alert("비밀번호를 입력하시오");
			$('#pw').focus();
			return false;
		}

		if ($('#pw2').val() == '') {
			alert("비밀번호 확인 입력을 해주세요.");
			$('#pw2').focus();
			return false;
		}

		//암호와 암호확인이 같은지 비교
		if ($('#pw').val() != $('#pw').val()) {
			alert("비밀번호가 다릅니다.");
			$('#pw').val('');//비밀번호 내용삭제
			$('#pw2').val('');//비밀번호 확인 내용삭제
			$('#pw').focus();//포커스 이동
			return false;
		}

		//이름
		if ($("#name").val() == '') {
			alert("이름을 입력 하시오");
			$("#name").focus();
			return false;
		}

		//모두 통과하면
		return true;
	}//function-end

	//Ajax
	function confirmIDCheck() {
		if ($('#id').val() == '') {
			alert("ID는 필수입력입니다.");
		} else {
			//id가 입력 되었을 때
			//$.ajax({});
			$.ajax({

				type : "POST",//type - post
				url : "confirmID",
				data : "id=" + $('#id').val(), //서버로 넘길 인수값(id)
				dataType : 'JSON', //서버가 보내준 자료 타입(json)
				success : function(data) { //서버가 보내준 데이타
					if (data.check == 1) {
						//사용가능
						alert("사용가능한 아이디입니다.");
						$('#pw').focus();//비밀번호로 포커스 이동
					} else if (data.check == -1) {
						//사용중인 아이디
						alert("사용중인 아이디입니다.");
						$('#id').val('');//아이디 내용삭제
						$('#id').focus();
					}
				}//success-end

			});
		}
	}//Ajax-confirmIDCheck-end
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
		<table board="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" id="id" size="20"> <input
					type="button" value="ID중복체크" onclick="confirmIDCheck()"></td>
			</tr>

			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pw" id="pw" size="10">
				</td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" name="pw2" id="pw2" size="10">
				</td>
			</tr>

			<tr>
				<td>성명</td>
				<td><input type="text" name="name" id="name" size="30">
				</td>
			</tr>

			<tr>
				<td>이메일</td>
				<td><input type="text" name="email1" id="email1" size="30">

					<select name="email2" id="email2">
						<option value="@naver.com">naver.com</option>
						<option value="@gmail.com">@gmail.com</option>
						<option value="@daum.net">@daum.net</option>
						<option value="@nate.com">@nate.com</option>
						<option value="@kakao.com">@kakao.com</option>
				</select></td>
			</tr>

			<!-- 전화번호  -->
			<tr>
				<td>전화번호</td>
				<td><select name="tel1" id="tel1">
						<option value="010">010</option>
						<option value="017">017</option>
						<option value="018">018</option>
				</select> <input type="text" name="tel2" id="tel2" size="4" /> <input
					type="text" name="tel3" id="tel3" size="4" /></td>
			</tr>

			<!-- 우편번호 -->
			<tr>
				<td>우편번호</td>
				<td><input type="text" name="zipcode" id="zipcode" size="7">
					<input type="button" value="주소검색" onClick="openDaumPostcode()">

				</td>
			</tr>
			
			<!-- 주소 -->
			<tr>
				<td>주소</td>
				<td>
					<input type="text" name="addr" id="addr" size="60" readonly>
					<br/>
					상세주소 : <input type="text" name="addr2" id="addr2" size="40">
				</td>
			</tr>
			
			<tr>
			<td>
				<input type="submit" value="회원가입">
				<input type="reset" value="다시입력">
				<input type="button" value="가입취소" onClick="location='main'">
			</td>
			</tr>
		</table>
	</form>
</body>
</html>