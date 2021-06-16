<!-- 필터를 적용해도 한글 깨짐이 있을 경우
charset과 pageEncoding을 utf-8로 변경 --> 

<%@page import="java.util.List"%>
<%@taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="/front/">이전</a>
	<table width="700" border="3" bordercolor="lightgray" align="center">
		<thead>
			<tr>
				<td>회원 고유 코드</td>
				<td>이메일</td>
				<td>닉네임</td>
			</tr>
		</thead>		
		<c:forEach var="member" items="${members}">
			<tr>
				<td>${member.uid}</td>
				<td>${member.email}</td>
				<td>${member.nickname}</td>
			</tr>
		</c:forEach>
	</table>	
</body>
</html>