<!-- 필터를 적용해도 한글 깨짐이 있을 경우
charset과 pageEncoding을 utf-8로 변경 -->

<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>웹프인사이드 로그인</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/userjoin.css"/>
</head>
<body>
<jsp:include page="../layout/header.jsp"/>
<div class="content">
    <form action="/front/member/login" method="post" class="join_form">
        <div>
            <label for="email"><b>이메일 주소</b></label>
            <input type="email" placeholder="id@example.com" name="email" id="email">
        </div>

        <div>
            <label for="pass"><b>비밀번호</b></label>
            <input type="password" name="pass" id="pass">
        </div>

        <div class="join_button">
            <button type="submit">로그인</button>
        </div>
    </form>
</div>
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>