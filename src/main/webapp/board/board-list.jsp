<%--
  Created by IntelliJ IDEA.
  User: Tofu
  Date: 2021-06-11
  Time: 오후 5:55
  To change this template use File | Settings | File Templates.
--%>

<%--SE게시판의 게시판 헤더입니다--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<html>
<head>
    <title>board-list</title>
</head>
<body>

<table width="700" border="3" bordercolor="lightgray" align="center">
    <thead>
    <tr>
<%--        <c:foreach var="board" items="${board.name}">--%>
<%--        <td><button type="submit" formaction="/front/board/${board.english}">${board.name}</button></td>--%>
<%--        </c:foreach>--%>
        <td><button type="submit" formaction="/front/board/freeboard">자유게시판</button></td>
        <td><button type="submit" formaction="/front/board/archieve">아콰이부</button> </td>
        <td><button type="submit" formaction="/front/board/notMouth">이것은 입에서 나는 소리가 아니여</button> </td>
    </tr>
    </thead>
</table>
</body>
</html>
