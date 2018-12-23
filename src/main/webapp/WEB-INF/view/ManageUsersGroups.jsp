<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
    <title>Zarządzanie grupami użytkowników</title>
</head>
<body>

<jsp:include page="/WEB-INF/view/fragments/header.jspf"/>

<p>Dodaj nową grupę:</p>
<form action="/ManageUsersGroups" method="post">
    <p>Nazwa nowej grupy &nbsp<input type="text" name="newUserGroup"></p>
    <input type="submit">
</form>

<table border="2">
    <tr>
        <th>ID grupy</th>
        <th>nazwa grupy</th>
        <th>akcja</th>
        <th>akcja</th>

    </tr>
    <c:forEach var="group" items="${userGroups}">

        <tr>
            <td>${group.id}</td>
            <td>${group.name}</td>
            <td><a href="/EditUsersGroup?id=${group.id}&name=${group.name}">edytuj</a></td>
            <td><a href="/DeleteUsersGroup?id=${group.id}&name=${group.name}">usuń</a></td>
        </tr>

    </c:forEach>

</table>


<jsp:include page="/WEB-INF/view/fragments/footer.jspf"/>

</body>
</html>
