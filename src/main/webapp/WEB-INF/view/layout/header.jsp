<%@ page import="com.domain.Member" %><%--
  Created by IntelliJ IDEA.
  User: Tofu
  Date: 2021-06-12
  Time: 오후 1:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/resources/header.css"/>
<script type="text/JavaScript" src="/resources/header.js"></script>
<header>
    <span class="openbtn" onclick="openNav()">&#9776; open</span>
    <div class="header">
        <div class="logo">
            <h1><a href="/front/">웹프인사이드</a></h1>
        </div>

        <div class="menu">
            <a href="/front/member/members">회원목록</a>
            <a href="/front/post/list">게시판</a>
        </div>

        <div class="util">
                <c:choose>
                    <c:when test="${!empty LOGIN}">
                        <%
                            Member member = (Member) request.getSession().getAttribute("LOGIN");
                        %>
                        <%=member.getNickname()%>님 안녕하세요.
                        <button onclick="window.location.href='/front/member/logout'">로그아웃</button>
                    </c:when>
                    <c:otherwise>
                        <button onclick="window.location.href='/front/member/login'">로그인</button>
                        <button onclick="window.location.href='/front/member/join'">회원가입</button>
                    </c:otherwise>
                </c:choose>
        </div>
    </div>
</header>
<div id="category" class="sidemenu">
    <a href="#" class="closebtn" onclick="closeNav()">&times;</a>
    <a href="/front/member/members">회원목록</a>
    <a href="/front/post/list">게시판</a>
    <c:choose>
        <c:when test="${!empty LOGIN}">
            <%
                Member member = (Member) request.getSession().getAttribute("LOGIN");
            %>
            <a href="#"><%=member.getNickname()%>님 안녕하세요.</a>
            <a href="/front/member/logout">로그아웃</a>
        </c:when>
        <c:otherwise>
            <a href="/front/member/login">로그인</a>
            <a href="/front/member/join">회원가입</a>
        </c:otherwise>
    </c:choose>
</div>

