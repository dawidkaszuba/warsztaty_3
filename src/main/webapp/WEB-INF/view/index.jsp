<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
    <title>Witaj</title>
</head>
<body>

<jsp:include page="/WEB-INF/view/fragments/header.jspf"/>

    <table border="2">
        <tr><th>ID zadania</th><th>rozwiązanie zadania</th><th>Szczegóły rozwiązania</th></tr>
        <c:forEach var="solution" items="${solutionlist}">

        <tr>
            <td>${solution.exerciseId}</td><td>${solution.description}</td><td><a href="/SolutionDetails?id=${solution.exerciseId}"/>szczegóły</td>
        </tr>


        </c:forEach>

    </table>

<jsp:include page="/WEB-INF/view/fragments/footer.jspf"/>

</body>
</html>
