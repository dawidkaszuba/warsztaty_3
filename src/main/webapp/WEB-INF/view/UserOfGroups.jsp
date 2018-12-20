<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/fragments/header.jspf"/>
    <table border="2">
        <tr>
            <th>ID użytkownika</th>
            <th>username</th>
            <th>email</th>
            <th>Wszystkie rozwiązania użytkownika</th>
        </tr>
            <c:forEach var="user" items="${userList}">

                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td><a href="/SolutionsByUser?id=${user.id}">wszystkie rozwiązania</a></td>
                </tr>


            </c:forEach>
    </table>
<jsp:include page="/WEB-INF/view/fragments/footer.jspf"/>
</body>
</html>
