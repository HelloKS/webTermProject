<%@page import="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
</head>
<body>
<jsp:include page="../layout/header.jsp"/>
<table width="700" border="3" bordercolor="lightgray" align="center">
    <thead>
    <tr>
        <td>no</td>
        <td>제 목</td>
        <td>글쓴이</td>
        <td>작성일</td>
        <td>조회수</td>
    </tr>
    </thead>
    <c:forEach var="board" items="${boards}">
        <tr>
            <td>${board.id}</td>
            <!--게시글 조회를 위한 href는 query string 사용, method = GET
            href는 상대경로 사용 -> 현재경로 board/board-list, 요청경로 board/detail + request parameter -->
            <td><a href="detail?id=${board.id}">${board.title}</a></td>
            <td>${board.writer}</td>
            <td>${board.regdate}</td>
            <td>${board.hit}</td>
        </tr>
    </c:forEach>
</table>
<div>
    <a href="write">글 작성</a>
</div>
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>