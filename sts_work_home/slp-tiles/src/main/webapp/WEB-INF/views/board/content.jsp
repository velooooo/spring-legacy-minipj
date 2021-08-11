<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2>글내용 보기</h2>
<table>
	<tr>
		<td>글번호</td>
		<td>${bdto.num}</td>
		
		<td>조회수</td>
		<td>${bdto.hit}</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>${bdto.writer}</td>
		
		<td>작성일</td>
		<td><fmt:formatDate value="${bdto.reg_date}" pattern="yyyy-MM-dd"/></td>
	</tr>
	<tr>
		<td>글제목</td>
		<td colspan="3">${bdto.subject}</td>
	</tr>
	<tr>
		<td>내용</td>
		<td colspan="3"><pre>${bdto.content}</pre></td>
	</tr>
	<tr>
		<td colspan="4" align="center">
			<input type="button" value="글수정" onClick="location.href='updateForm?num=${num}&pageNum=${pageNum}'"/>
			
			<input type="button" value="글삭제" onClick="location.href='delete?num=${num}&pageNum=${pageNum}'"/>
			
			<input type="button" value="새글쓰기" onClick="location.href='writeForm'"/>
			
			<input type="button" value="답글쓰기" onClick="location.href='writeForm?num=${num}&pageNum=${pageNum}&re_group=${bdto.re_group}&re_step=${bdto.re_step}&re_depth=${bdto.re_depth}'"/>
			
			<input type="button" value="글목록" onClick="location.href='basic-list?pageNum=${pageNum}'"/>
		</td>
	</tr>
</table>
