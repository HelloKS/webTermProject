<%--
  Created by IntelliJ IDEA.
  User: Tofu
  Date: 2021-06-11
  Time: 오후 5:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="post.list">
    <ul>
        <c:foreach value="post" items="posts">
            <li>
                <a href="/front/${post.english}">${post.name}</a>
            </li>
        </c:foreach>
    </ul>
</nav>