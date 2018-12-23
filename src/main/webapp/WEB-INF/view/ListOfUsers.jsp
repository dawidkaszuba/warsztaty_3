<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
    <title>Lista wszystkich uzytkowników</title>
</head>
<body>

<jsp:include page="/WEB-INF/view/fragments/header.jspf"/>

<table border="2">
    <tr>
        <th>ID użytkownika</th>
        <th>nazwa</th>
        <th>email</th>

    </tr>
    <p><a href="/ManageUsers">-->>Zarządzaj użytkownikami</a></p>

    <c:forEach var="user" items="${userList}">

        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>

        </tr>

    </c:forEach>
</table>


<jsp:include page="/WEB-INF/view/fragments/footer.jspf"/>

</body>
</html>
