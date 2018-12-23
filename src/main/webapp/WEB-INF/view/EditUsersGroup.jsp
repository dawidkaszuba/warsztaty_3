<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
    <title>Edytowanie grupy</title>
</head>
<body>

<%

    javax.servlet.http.Cookie cookie
            = new javax.servlet.http.Cookie("id", request.getParameter("id"));

    response.addCookie(cookie);

%>

<form action="/EditUsersGroup" method="post">


    <p>Podaj nową nazwę grupy "${userGroup.name}" o id= "${userGroup.id}"&nbsp<input type="text" name="newNameOfGroup">
    </p>
    <p><input type="submit"></p>

</form>

</body>
</html>
