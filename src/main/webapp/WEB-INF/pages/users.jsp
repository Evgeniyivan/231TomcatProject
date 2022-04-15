<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Пользователи</title>
</head>
<body>

<h2>Пользователи</h2>
<table>
    <tr>
        <th>id</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Возраст</th>
        <th>Действие</th>
    </tr>
    <c:forEach var="user" items="${usersList}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.lastName}</td>
            <td>${user.age}</td>
            <td>
                <a href="/edit/${user.id}">Редактировать</a>
                <a href="/delete/${user.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Добавить</h2>
<c:url value="/add" var="add"/>
<a href="${add}">Добавить нового пользователя</a>
</body>
</html>