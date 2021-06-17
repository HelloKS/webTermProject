<%--
  Created by IntelliJ IDEA.
  User: HelloKS
  Date: 2021-06-08
  Time: 오후 8:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>글 수정</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
</head>
<body>
<jsp:include page="../layout/header.jsp"/>
<form action="/front/post/modify" method="post">
    <input type="hidden" name="id" value="<c:out value="${post.id}"/>">
    <table width="700" border="3" bordercolor="lightgray" align="center">
        <thead>
        <tr>
            <td>게시글 ID</td>
            <td><c:out value="${post.id}"/></td>
        </tr>
        <tr>
            <td>제목</td>
            <td><input type="text" id="title" name="title" value="<c:out value="${post.title}"/>"></td>
        </tr>
        <tr>
            <td>작성자 이름</td>
            <td><input readonly type="text" id="writer" name="writer" value="<c:out value="${post.writerId}"/>"></td>
        </tr>
        <tr>
            <td>게시판 분류</td>
            <td>
                <select name="board">
                    <c:forEach var="board" items="${boards}">
                        <option value="${board.id}">${board.name}: ${board.desc}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>내용</td>
            <td>
                <textarea id="content" name="content" rows="5" cols="33"><c:out value="${post.contents}"/></textarea>
            </td>
        </tr>
        </tbody>
    </table>
    <input type="submit" value="게시글 수정"/>
</form>
<a href="/post/list">취소</a>
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>
