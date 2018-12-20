<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Grupy</title>


</head>
<body>
<jsp:include page="/WEB-INF/view/fragments/header.jspf"/>
<table border="2">
    <tr><th>ID grupy</th><th>nazwa grupy</th><th>szczegóły</th></tr>
    <c:forEach var="group" items="${groupList}">

        <tr>
            <td>${group.id}</td><td>${group.name}</td><td><a href="/UsersOfGroup?id=${group.id}">użytkownicy grupy</a></td>
        </tr>


    </c:forEach>

</table>

<p><a href="/ManageUsersGroups">Zarządzaj grupami uzytkowników</a></p>


<jsp:include page="/WEB-INF/view/fragments/footer.jspf"/>
</body>
</html>
