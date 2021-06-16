<%--
  Created by IntelliJ IDEA.
  User: HelloKS
  Date: 2021-06-08
  Time: 오후 8:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>글 읽기</title>
</head>
<body>
<table width="700" border="3" bordercolor="lightgray" align="center">
    <thead>
    <tr>
        <td>게시글 번호</td>
        <td><c:out value="${board.id}"/></td>
    </tr>
    <tr>
        <td>제목</td>
        <td><c:out value="${board.title}"/></td>
    </tr>
    <tr>
        <td>작성자 이름</td>
        <td><c:out value="${board.writer}"/></td>
    </tr>
    <tr>
        <td>작성 일자</td>
        <td><c:out value="${board.regdate}"/></td>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>내용</td>
        <td><c:out value="${board.contents}"/></td>
    </tr>
    </tbody>
</table>
<a href="list">뒤로 가기</a>
<a href="modify?id=<c:out value="${board.id}"/>">수정</a>
<a href="delete?id=<c:out value="${board.id}"/>">삭제</a>
</body>
</html>
