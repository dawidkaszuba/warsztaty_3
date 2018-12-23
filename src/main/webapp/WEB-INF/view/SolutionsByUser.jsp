
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
    <title>Title</title>
</head>
<body>

<jsp:include page="/WEB-INF/view/fragments/header.jspf"/>
<table border="2">
    <tr>
        <th>ID rozwiązania</th>
        <th>utworzono</th>
        <th>edytowano</th>
        <th>rozwiązanie</th>
        <th>ocena</th>
        <th>komentarz</th>
    </tr>
    <c:forEach var="solution" items="${solutionsList}">

        <tr>
            <td>${solution.id}</td>
            <td>${solution.created}</td>
            <td>${solution.updated}</td>
            <td>${solution.description}</td>
            <td>${solution.mark}</td>
            <td>${solution.comment}</td>
        </tr>

    </c:forEach>
</table>
<jsp:include page="/WEB-INF/view/fragments/footer.jspf"/>

</body>
</html>
