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
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta charset="utf-8">
    <title>글 읽기</title>
</head>
<body>
<h2 style="text-align: center">게시글 열람</h2>
<table width="100%" border="3" bordercolor="lightgray" align="center">
    <thead>
    <tr>
        <td width="30%">게시글 번호</td>
        <td width="60%"><c:out value="${post.id}"/></td>
    </tr>
    <tr>
        <td>제목</td>
        <td><c:out value="${post.title}"/></td>
    </tr>
    <tr>
        <td>작성자 이름</td>
        <td><c:out value="${post.writerId}"/></td>
    </tr>
    <tr>
        <td>작성 일자</td>
        <td><c:out value="${post.regdate}"/></td>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>내용</td>
        <td><c:out value="${post.contents}"/></td>
    </tr>
    </tbody>
</table>
<a href="list">뒤로 가기</a>
<a href="modify?id=<c:out value="${post.id}"/>">수정</a>
<a href="delete?id=<c:out value="${post.id}"/>">삭제</a>
</body>
</html>
