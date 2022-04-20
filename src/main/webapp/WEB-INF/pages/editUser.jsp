<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Edit User</title>
</head>
<body>
<div align="center">
    <h2>Edit User</h2>
    <form:form action="/edit" method="post" modelAttribute="user">
        <table border="0" cellpadding="5">
            <tr>
                <td>ID: </td>
                <td>${user.id}
                    <form:hidden path="id"/>
                </td>
            </tr>
            <tr>
                <td>Name: </td>
                <td><form:input path="firstName" /></td>
            </tr>
            <tr>
                <td>Last Name: </td>
                <td><form:input path="lastName" /></td>
            </tr>
            <tr>
                <td>age: </td>
                <td><form:input path="age" /></td>
            </tr>
            <tr>
                <td>Email: </td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>