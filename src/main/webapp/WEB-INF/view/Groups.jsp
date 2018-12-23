<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
    <title>Grupy</title>
</head>
<body>

<jsp:include page="/WEB-INF/view/fragments/header.jspf"/>

<p><a href="/ManageUsersGroups">-->>Zarządzaj grupami użytkowników</a></p>

<table border="2">
    <tr><th>ID grupy</th><th>nazwa grupy</th><th>szczegóły</th></tr>
    <c:forEach var="group" items="${groupList}">

        <tr>
            <td>${group.id}</td><td>${group.name}</td><td><a href="/UsersOfGroup?id=${group.id}">użytkownicy grupy</a></td>
        </tr>


    </c:forEach>

</table>




<jsp:include page="/WEB-INF/view/fragments/footer.jspf"/>
</body>
</html>
