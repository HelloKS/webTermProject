<%@page import="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <script>
        /*let boardMenu = document.getElementsByClassName('boardList');

        for(var i = 0; i < boardMenu.length; i++){
            boardMenu[i].addEventListener('mouseover', changeBackground(), false);
            boardMenu[i].addEventListener('mouseout', changeBackground(), false);
        }
        function changeBackground(event){
            var color = this.style.backgroundColor;
            if(event.type === "mouseover"){
                boardMenu[i].style.backgroundColor = "gray";
            }else{
                boardMenu[i].style.backgroundColor = color;
            }
        }*/
        function changeTableRowColor(row){
            row.style.backgroundColor = "cyan";
        }
        function restoreTableRowColor(row){
            row.style.backgroundColor = "white";
        }
    </script>
</head>
<body>
<jsp:include page="../layout/header.jsp"/>
<div class="content">
    <c:choose>
        <c:when test="${!empty board}">
            <h1 style="text-align: center">게시물: <c:out value="${board.name}"/></h1>
            <h2 style="text-align: center"><c:out value="${board.desc}"/></h2>
        </c:when>
        <c:otherwise>
            <h1 style="text-align: center">게시물: 전체 게시판</h1>
        </c:otherwise>
    </c:choose>
    <div style="text-align: center">
        <a href="/front/post/list" class="boardList">전체</a>
        <c:forEach var="bd" items="${allboard}">
            <a href="/front/post/list?bdid=${bd.id}" class="boardList">${bd.name}</a>
        </c:forEach>
    </div>
    <table class="itemList" width="100%" border="1" bordercolor="black" align="center">
        <thead>
        <tr>
            <td width="10%">no</td>
            <td width="45%">제 목</td>
            <td width="15%">글쓴이</td>
            <td width="15%">작성일</td>
            <td width="10%">조회수</td>
        </tr>
        </thead>
        <c:forEach var="post" items="${posts}">
            <tr onmouseover="changeTableRowColor(this)" onmouseout="restoreTableRowColor(this)">
                <td>${post.id}</td>
                <!--게시글 조회를 위한 href는 query string 사용, method = GET
                href는 상대경로 사용 -> 현재경로 board/board-list, 요청경로 board/detail + request parameter -->
                <td><a href="detail?id=${post.id}">${post.title}</a></td>
                <td>${post.writerName}</td>
                <td>${post.regdate}</td>
                <td>${post.hit}</td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${!empty LOGIN}">
        <div style="text-align: center">
            <a href="write">글 작성</a>
        </div>
    </c:if>
    <div style="text-align: center">
        <c:if test="${curpage > 1}">
            <c:choose>
                <c:when test="${!empty board}">
                    <a href="/front/post/list?bdid=<c:out value="${board.id}"/>&page=<c:out value="${curpage-1}"/>"><</a>
                </c:when>
                <c:otherwise>
                    <a href="/front/post/list?page=<c:out value="${curpage-1}"/>"><</a>
                </c:otherwise>
            </c:choose>
        </c:if>
        &nbsp;<c:out value="${curpage}"/>&nbsp;
        <c:if test="${posts.size() == 10}">
            <c:choose>
                <c:when test="${!empty board}">
                    <a href="/front/post/list?bdid=<c:out value="${board.id}"/>&page=<c:out value="${curpage+1}"/>">></a>
                </c:when>
                <c:otherwise>
                    <a href="/front/post/list?page=<c:out value="${curpage+1}"/>">></a>
                </c:otherwise>
            </c:choose>
        </c:if>
    </div>
</div>
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>