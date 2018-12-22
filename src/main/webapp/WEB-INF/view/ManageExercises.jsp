<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Zarządzaj zadaniami</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/fragments/header.jspf"/>

<table border="2">
    <tr>
        <th>Id zadania</th>
        <th>nazwa</th>
        <th>opis</th>
        <th>akcja</th>
        <th>akcja</th>
    </tr>

    <c:forEach var="exercise" items="${exercises}">
        <tr>
            <td>${exercise.id}</td>
            <td>${exercise.title}</td>
            <td>${exercise.description}</td>
            <td><a href="/EditExercises?id=${exercise.id}&name=${exercise.title}">edytuj</a></td>
            <td><a href="DeleteExercise?id=${exercise.id}">usuń</a></td>
        </tr>

    </c:forEach>
</table>

<p>Dodaj nowe zadanie:</p>
<form action="/ManageExercises" method="post">
    <p>Tytuł zadania: &nbsp<input type="text" name="titleExercises"></p>
    <p>Opis zadania: &nbsp<input type="text" name="descriptionExercises"></p>
    <p><input type="submit"></p>
</form>



<jsp:include page="/WEB-INF/view/fragments/footer.jspf"/>
</body>
</html>
