<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Zarządzaj użytkownikami</title>
</head>
<body>

<jsp:include page="/WEB-INF/view/fragments/header.jspf"/>

<table border="2">
    <tr>
        <th>ID użytkownika</th>
        <th>nazwa</th>
        <th>email</th>
        <th>akcja</th>
        <th>akcja</th>

    </tr>
    <c:forEach var="user" items="${usersList}">

        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td><a href="/EditUser?id=${user.id}&name=${user.username}">edytuj</a></td>
            <td><a href="/DeleteUser?id=${user.id}&name=${user.username}">usuń</a></td>
        </tr>

    </c:forEach>

</table>
<p>Dodaj nowego użytkownika:</p>
<form action="/ManageUsers" method="post">
    <p>Nazwa:&nbsp<input type="text" name="newUserName"></p>
    <p>email:&nbsp<input type="text" name="newUserEmail"></p>
    <input type="submit">
</form>

<jsp:include page="/WEB-INF/view/fragments/footer.jspf"/>

</body>
</html>
