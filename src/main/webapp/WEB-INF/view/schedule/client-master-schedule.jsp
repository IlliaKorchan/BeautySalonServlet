<%--
Created by IntelliJ IDEA.
User: koill
Date: 05.06.2019
Time: 12:57
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
<!doctype html>
<html lang="${language}">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">

    <title><fmt:message key="title.schedule"/></title>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light sticky-top"
     style="background-color: #FFE3F5 ">
    <a href="${pageContext.request.contextPath}/salon/menu" class="navbar-brand">
        <img src="https://image.freepik.com/free-vector/_53876-43323.jpg" width="30" height="30" alt="logo">
    </a>
    <a href="${pageContext.request.contextPath}/salon/client-appointments" class="navbar-brand letter"
       style="color: deeppink; font-size:11pt"><fmt:message key="navbar.my.appointments"/></a>
    <a href="${pageContext.request.contextPath}/salon/client-master-schedule" class="navbar-brand letter"
       style="color: deeppink; font-size:11pt" ><fmt:message key="navbar.user.appoint"/></a>
    <a href="${pageContext.request.contextPath}/salon/procedures" class="navbar-brand letter"
       style="color: deeppink; font-size:11pt" ><fmt:message key="navbar.procedures"/></a>
    <a href="${pageContext.request.contextPath}/salon/client-reviews" class="navbar-brand letter"
       style="color: deeppink; font-size:11pt" ><fmt:message key="navbar.user.review"/></a>
    <a href="${pageContext.request.contextPath}/logout" class="navbar-brand letter"
       style="color: deeppink; font-size:11pt"><fmt:message key="navbar.logout"/></a>
    <form method="get" action="${pageContext.request.contextPath}/change-language/salon/client-master-schedule">
        <label for="language"></label>
        <select id="language" name="language"
                onchange="submit()" style="font-size: 11pt; background-color: #FFE3F5; color: deeppink">
            <option value="en" ${language == 'en' ? 'selected' : ''} style="color: deeppink">English</option>
            <option value="uk" ${language == 'uk' ? 'selected' : ''} style="color: deeppink">Українська</option>
        </select>
    </form>
</nav>
<br>

<form method="post" action="${pageContext.request.contextPath}/salon/client-master-schedule">
    <p align="center" style="color: deeppink"><fmt:message key="choose.master"/><br>
        <select required id="masterId" name="masterId"
                style="font-size: 11pt; background-color: #FFE3F5; color: deeppink">
            <c:forEach var="master" items="${requestScope.masters}">
                <option value="${master.user.id}" style="color: deeppink">${master.name}</option>
            </c:forEach>
        </select>
        <br><br>
        <button class="btn btn-success" style="background-color: #FFA9EB" type="submit">
            <fmt:message key="button.find"/>
        </button>
    </p>
</form>

<form method="post" action="${pageContext.request.contextPath}/salon/client-master-schedule">
    <p style="color: deeppink" align="center"><fmt:message key="choose.date"/><br>
        <select required id="date" name="date"
                style="font-size: 11pt; background-color: #FFE3F5; color: deeppink" onchange="">
            <c:forEach var="day" items="${requestScope.workingDays}">
                <option value="${day}" style="color: deeppink">${day}</option>
            </c:forEach>
        </select>
        <br><br>
        <button class="btn btn-success" style="background-color: #FFA9EB" type="submit">
            <fmt:message key="button.find"/>
        </button>
    </p>
</form>

<form method="post" action="${pageContext.request.contextPath}/salon/make-appointment">
    <p align="center"><fmt:message key="choose.procedure"/><br>
        <select required id="selectedProcedure" name="selectedProcedure"
                style="font-size: 11pt; background-color: #FFE3F5; color: deeppink">
            <c:forEach var="procedure" items="${requestScope.procedures}">
                <option value="${procedure.name}" style="color: deeppink">${procedure.name}</option>
            </c:forEach>
        </select>
        <br><br>
    </p>
    <p align="center"><fmt:message key="choose.time"/><br>
        <select required id="time" name="time"
                style="font-size: 11pt; background-color: #FFE3F5; color: deeppink">
            <c:forEach var="time" items="${requestScope.freeTimes}">
                <option value="${time}" style="color: deeppink">${time}</option>
            </c:forEach>
        </select>
        <br><br>
        <button class="btn btn-success" style="background-color: #FFA9EB" type="submit">
            <fmt:message key="navbar.user.appoint"/>
        </button>
    </p>
</form>
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
