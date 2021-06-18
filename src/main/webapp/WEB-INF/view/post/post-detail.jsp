<%--
  Created by IntelliJ IDEA.
  User: HelloKS
  Date: 2021-06-08
  Time: 오후 8:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta charset="utf-8">
    <title>글 읽기</title>
    <script>
        function bigger(){
            document.getElementById('backButton').style.fontSize = "20px";
        }
    </script>
</head>
<body>
<jsp:include page="../layout/header.jsp"/>
<h2 style="text-align: center">게시글 열람</h2>
<div class="content">
    <table width="100%" border="3" bordercolor="lightgray" align="center">
        <thead>
        <tr>
            <td width="30%">게시글 번호</td>
            <td width="60%"><c:out value="${post.id}"/></td>
        </tr>
        <tr>
            <td>제목</td>
            <td><c:out value="${post.title}"/></td>
        </tr>
        <tr>
            <td>게시판</td>
            <td><c:out value="${board.name}"/></td>
        </tr>
        <tr>
            <td>작성자 이름</td>
            <td><c:out value="${post.writerName}"/></td>
        </tr>
        <tr>
            <td>작성 일자</td>
            <td><c:out value="${post.regdate}"/></td>
        </tr>
        <tr>
            <td>조회수</td>
            <td><c:out value="${post.hit}"/></td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td colspan="2"><c:out value="${post.contents}"/></td>
        </tr>


        <form action="/front/post/detail" method="post">
            <input type="hidden" name="id" value="${post.id}"/>
            <table width = "100%" border="3" bordercolor="lightgray">
                <tr>
                    <td>추천수</td>
                    <td></td>
                </tr>
                <tr>
                    <td><c:out value="${agreeCount}"/></td>
<c:if test="${!empty LOGIN}">
                    <td><button class="agree">추천 꾹!</button></td>
</c:if>
                </tr>
            </table>
        </form>


        <!-- 댓글 부분 -->
        <table width = "100%" border="1" bordercolor="lightgray">
            <c:if test="${requestScope.comments != null}">
                <c:forEach var="comment" items="${requestScope.comments}">
                    <tr>
                        <!-- 아이디, 작성날짜 -->
                        <td width="25%">
                            <div>
                                    ${comment.writer}<br>
                                <font size="2" color="#d3d3d3">${comment.cmt_date}</font>
                            </div>
                        </td>
                        <!-- 본문 내용 -->
                        <td width="60%">
                            <div class="text_wrapper">
                                    ${comment.cmt_content}
                            </div>
                        </td>
                        <!-- 버튼 -->
                        <td width="15%">
                            <div id="btn" style="text-align:center;">
                                <!-- 댓글 작성자만 삭제 -->
                                <c:if test="${!empty LOGIN}">
                                    <a href="commentDelete?id=<c:out value="${post.id}"/>&cmt_id=<c:out value="${comment.cmt_id}"/>">[삭제]</a>
                                </c:if>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <!-- 로그인 했을 경우에만 댓글 작성 가능 -->
            <c:if test="${!empty LOGIN}">
                <tr>
                    <!-- 아이디 -->
                    <td width="25%">
                        <div>
                            댓글 작성
                        </div>
                    </td>
                    <!-- 본문 작성 -->
                    <form action="/front/post/detail" method="post">
                        <td width="60%">
                            <div>
                                <input type="hidden" name="id" value="${post.id}">
                                <textarea name="comment_content" rows="4" cols="70"></textarea>
                            </div>
                        </td>
                        <!-- 댓글 등록 버튼 -->
                        <td width="15%">
                            <input type="submit" value="등록" style="text-align:center;">
                        </td>
                    </form>
                </tr>
            </c:if>
        </table>
        </tbody>
    </table>
    <a href="list" id="backButton" onmousedown="bigger()">뒤로 가기</a>
    <c:if test="${!empty LOGIN}">
        <a href="modify?id=<c:out value="${post.id}"/>">수정</a>
        <a href="delete?id=<c:out value="${post.id}"/>">삭제</a>
    </c:if>
</div>
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>
