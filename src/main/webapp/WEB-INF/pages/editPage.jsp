
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <c:if test="${empty user.name}">
        <title>Добавить</title>
    </c:if>
    <c:if test="${!empty user.name}">
        <title>Редактировать</title>
    </c:if>
</head>
<body>
<c:if test="${empty user.name}">
    <c:url value="/add" var="var"/>
</c:if>
<c:if test="${!empty user.name}">
    <c:url value="/edit" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <c:if test="${!empty user.name}">
        <input type="hidden" name="id" value="${user.id}">
    </c:if>
    <label for="name">Имя</label>
    <input type="text" name="name" id="name">
    <label for="lastName">Фамиля</label>
    <input type="text" name="lastName" id="lastName">
    <label for="age">Возраст</label>
    <input type="text" name="age" id="age">

    <c:if test="${empty user.name}">
        <input type="submit" value="Добавить">
    </c:if>
    <c:if test="${!empty user.name}">
        <input type="submit" value="Изменить">
    </c:if>
</form>
</body>
</html>