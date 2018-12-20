<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Solution</title>
</head>
<body>

<jsp:include page="/WEB-INF/view/fragments/header.jspf"/>

<table border="2">

    <tr>
        <th>Utworzone</th>
        <th>edytowane</th>
        <th>rozwiązanie</th>
        <th>id zadania</th>
        <th>id użytkownika</th>
        <th>ocena</th>
        <th>komentarz</th>
    </tr>
    <tr>
        <td>${solution.created}</td>
        <td>${solution.updated}</td>
        <td>${solution.description}</td>
        <td>${solution.exerciseId}</td>
        <td>${solution.userId}</td>
        <td>${solution.mark}</td>
        <td>${solution.comment}</td>
    </tr>

</table>

<jsp:include page="/WEB-INF/view/fragments/footer.jspf"/>
</body>
</html>
