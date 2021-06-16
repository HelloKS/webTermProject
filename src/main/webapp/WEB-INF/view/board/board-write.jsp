<%--
  Created by IntelliJ IDEA.
  User: HelloKS
  Date: 2021-06-08
  Time: 오후 8:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>글 쓰기</title>
</head>
<body>
<jsp:include page="../layout/header.jsp"/>
<div class="content">
    <form action="/front/board/write" method="post">
        <table width="700" border="3" bordercolor="lightgray" align="center">
            <thead>
            <tr>
                <td>제목</td>
                <td><input type="text" id="title" name="title"></td>
            </tr>
            <tr>
                <td>작성자 이름</td>
                <td><input type="text" id="writer" name="writer"></td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>내용</td>
                <td>
                    <textarea id="content" name="content" rows="5" cols="33"></textarea>
                </td>
            </tr>
            </tbody>
        </table>
        <input type="submit" value="게시글 작성"/>
    </form>
</div>
<div>
    <a href="list">뒤로 가기</a>
</div>
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>
