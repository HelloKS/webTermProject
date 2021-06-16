<!-- 필터를 적용해도 한글 깨짐이 있을 경우
charset과 pageEncoding을 utf-8로 변경 -->

<%@page import="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 리스트</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
</head>
<body>
<jsp:include page="../layout/header.jsp"/>
<h2 style="text-align: center">회원리스트</h2>
<div class="content">
    <table width="100%" border="1" bordercolor="black" align="center">
        <thead>
        <tr>
            <td width="15%">고유 코드</td>
            <td width="35%">이메일</td>
            <td width="35%">닉네임</td>
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
</div>
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>