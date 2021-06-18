<%--
  Created by IntelliJ IDEA.
  User: HelloKS
  Date: 2021-06-08
  Time: 오후 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>추천 처리 성공</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
</head>
<body>
<jsp:include page="../layout/header.jsp"/>
<div class="content">
    추천 처리가 완료되었습니다.
    <a href="/front/post/detail?id=<c:out value="${articleid}"/>">게시물로 돌아가기</a>
</div>
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>
