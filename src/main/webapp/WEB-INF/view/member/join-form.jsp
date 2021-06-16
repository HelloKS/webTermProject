<!-- 필터를 적용해도 한글 깨짐이 있을 경우
charset과 pageEncoding을 utf-8로 변경 -->

<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>웹프인사이드 회원가입</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/userjoin.css"/>
</head>
<body>
<jsp:include page="../layout/header.jsp"/>
<form action="/front/member/join" method="post" class="join_form">
    <div>
        <label for="email"><b>이메일 주소</b></label>
        <input type="email" placeholder="id@example.com" name="email" id="email">
    </div>

    <div>
        <label for="pass"><b>비밀번호</b></label>
        <input type="password" name="pass" id="pass">
    </div>

    <div>
        <label for="nick"><b>사이트 닉네임</b></label>
        <input type="text" name="nick" id="nick">
    </div>

    <div class="join_button">
        <button type="submit">회원가입</button>
    </div>
</form>
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>