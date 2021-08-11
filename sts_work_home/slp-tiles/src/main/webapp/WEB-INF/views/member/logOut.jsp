<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--logOut--%>
<!-- 1초 뒤 메인으로 돌아감. -->
<meta http-equiv="refresh" content="1;main" />
	<%--세션 무효화 --%>
	<c:remove var="id" scope="session" />
	Bye
