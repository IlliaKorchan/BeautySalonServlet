<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%--
  Created by IntelliJ IDEA.
  User: koill
  Date: 05.06.2019
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>

    <body>
    Hello, ${sessionScope.user.name}! You are ${sessionScope.role}<br>
    <form action="${pageContext.request.contextPath}/logout" method="get">
    <input class="button" type="submit" value="Выйти">
    </form>
    </body>
</html>
