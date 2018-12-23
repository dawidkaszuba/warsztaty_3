<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
    <title>Edycja zadania</title>
</head>
<body>

<%

    javax.servlet.http.Cookie cookie
            = new javax.servlet.http.Cookie("id", request.getParameter("id"));

    response.addCookie(cookie);

%>
<p>Edytuj zadanie:</p>
<form action="/EditExercises" method="post">
    <p>Nowy tytuł zadania &nbsp<input type="text" name="newTitle"></p>
    <p>Nowa treść zadania &nbsp<input type="text" name="newDescription"></p>
    <p><input type="submit"></p>
</form>



</body>
</html>
