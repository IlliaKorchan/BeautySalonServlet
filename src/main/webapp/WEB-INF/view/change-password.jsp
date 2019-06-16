<%--
  Created by IntelliJ IDEA.
  User: koill
  Date: 09.06.2019
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="message"/>
<html lang="${language}">
<head>
    <title><fmt:message key="button.edit.password"/></title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light sticky-top"
     style="background-color: #FFE3F5 ">
    <a href="${pageContext.request.contextPath}/salon/menu" class="navbar-brand">
        <img src="https://image.freepik.com/free-vector/_53876-43323.jpg" width="30" height="30" alt="logo">
    </a>

    <form method="get" action="${pageContext.request.contextPath}/change-language/salon/change-password">
        <label for="language"></label>
        <select id="language" name="language"
                onchange="submit()" style="font-size: 11pt; background-color: #FFE3F5; color: deeppink">
            <option value="en" ${language == 'en' ? 'selected' : ''} style="color: deeppink">English</option>
            <option value="uk" ${language == 'uk' ? 'selected' : ''} style="color: deeppink">Українська</option>
        </select>
    </form>
</nav>

<div class="form">
    <p align="center" style="color: deeppink; font-size: 22pt"><fmt:message key="button.edit.password"/></p>
    <br>
    <form method="post" action="${pageContext.request.contextPath}/salon/change-password-commit">
        <p align="center">
            <fmt:message key="field.enter.current.password"/>: <input type="password" required placeholder="password" name="current-password"><br>
            <fmt:message key="field.enter.new.password"/>: <input type="password" required placeholder="new password" name="new-password"><br><br>
            <button class="btn btn-success" style="background-color: #FFA9EB"><fmt:message key="button.edit.password"/></button>
        </p>
    </form>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous">
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous">
</script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous">

</script>
</body>
</html>
