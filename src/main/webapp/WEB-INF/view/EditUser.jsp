<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edytowanie użytkownika</title>

</head>
<body>
<%

    javax.servlet.http.Cookie cookie
            = new javax.servlet.http.Cookie("id", request.getParameter("id"));

    response.addCookie(cookie);

%>


<form action="EditUser" method="post" id="newUser">
    <p>Nowa nazwa użytkownika:&nbsp<input type="text" name="userName"></p>
    <p>Nowy adres e-mail użytkownika: &nbsp<input type="text" name="userEmail"></p>

    <p>Wybierz nową grupe użytkownika: &nbsp<select name="userGroupId" form="newUser">
        <c:forEach var="group" items="${userGroups}">
            <option value="${group.id}">${group.name}</option>
        </c:forEach>
    </select></p>

    <p><input type="submit"></p>
</form>

</body>
</html>
