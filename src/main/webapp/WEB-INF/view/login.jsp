<%--
  Created by IntelliJ IDEA.
  User: koill
  Date: 05.06.2019
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>

    <body>
        <div class="form">
            <h1>Вход в систему</h1><br>
            ${requestScope.message}
            <form method="post" action="${pageContext.request.contextPath}/salon/menu">
                <input type="text" required placeholder="login" name="login"><br>
                <input type="password" required placeholder="password" name="password"><br><br>
                <input class="button" type="submit" value="Войти">
            </form>
        </div>
    </body>
</html>
