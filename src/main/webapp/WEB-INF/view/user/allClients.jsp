<%--
  Created by IntelliJ IDEA.
  User: tgenman
  Date: 29/10/2018
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Clients</title>
</head>
<body>
    <div> <a href="/">Home page</a> </div>

    <div><h2>All Clients:</h2></div>

    <div>
        <c:forEach var="user" items="${clients}">
            <div><b>${user.name} ${user.familyName}</b></div>
            <div><i>email: ${user.email}</i></div>
            <div><i>id: ${user.id}</i></div>
        </c:forEach>
    </div>
</body>
</html>
