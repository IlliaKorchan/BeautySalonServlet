<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Илья Корчан
  Date: 06.05.2019
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
    <h3>Hi!</h3>
    <c:forEach var="users" items="${requestScope.users}">
        Имя: <c:out value="${users.user.login}"/>
    </c:forEach>
    </body>
</html>