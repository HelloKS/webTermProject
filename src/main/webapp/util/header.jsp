<%--
  Created by IntelliJ IDEA.
  User: Tofu
  Date: 2021-06-12
  Time: 오후 1:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="header.css">
<header class="header.header">
    <h1><a href="/front">SE BOARD</a></h1>

    <div class="header.util">
        <!-- ACCOUNT -->
        <form method="post" class="account" >
            <button name="login">로그인</button>
        </form>
        <!-- /ACCOUNT -->

        <!-- SEARCH -->
        <form method="get" class="search" >
                <input type="text" name="search">
                <button type="submit">검색</button>
        </form>
        <!-- /SEARCH -->
    </div>
</header>
<div>
    <%@ include file="/post/post-list.jsp"%>
</div>