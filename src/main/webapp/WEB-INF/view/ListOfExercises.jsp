<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style><%@include file="/WEB-INF/css/style.css"%></style>

    <title>Zadania</title>
</head>
<body>

<jsp:include page="/WEB-INF/view/fragments/header.jspf"/>



<p><a href="/ManageExercises">Zarządzaj zadaniami</a></p>

<table border="2">
    <tr>
        <th>ID zadania</th>
        <th>nazwa</th>
        <th>opis</th>

    </tr>
    <c:forEach var="exercise" items="${exercisesList}">

        <tr>
            <td>${exercise.id}</td>
            <td>${exercise.title}</td>
            <td>${exercise.description}</td>
        </tr>

    </c:forEach>
</table>


<jsp:include page="/WEB-INF/view/fragments/footer.jspf"/>

</body>
</html>
