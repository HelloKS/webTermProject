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
    <title>글 쓰기</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
</head>
<body>
<jsp:include page="../layout/header.jsp"/>
<h2 style="text-align: center">게시글 작성</h2>
<div class="content">
    <form action="/front/board/write" method="post">
        <table width="80%" border="3" bordercolor="lightgray" align="center">
            <thead>
            <tr>
                <td width="30%">제목</td>
                <td width="60%"><input style="width: 100%" type="text" id="title" size="30" name="title"></td>
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
                    <textarea id="content" style="width: 100%; resize: vertical" name="content" rows="5"></textarea>
                </td>
            </tr>
            </tbody>
        </table>
        <div style="text-align: center">
            <input type="submit" value="게시글 작성"/>
        </div>
    </form>
</div>
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>
