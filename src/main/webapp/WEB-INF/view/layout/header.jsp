<%--
  Created by IntelliJ IDEA.
  User: Tofu
  Date: 2021-06-12
  Time: 오후 1:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="/resources/header.css"/>
<div class="header">
    <div>
        <h1><a href="/front/">웹프인사이드</a></h1>
    </div>

    <div class="util">
        <button onclick="window.location.href='/front/member/login'">로그인</button>

        <form method="get" class="search" >
            <input type="text" name="search">
            <button type="submit">검색</button>
        </form>
    </div>
</div>
