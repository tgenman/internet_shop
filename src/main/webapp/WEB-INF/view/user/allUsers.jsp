<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="All Users"  scope="session"/>
<c:import url="../templates/header.jsp" />

    <div>
        <c:forEach var="user" items="${users}">
            <div><b>${user.name} ${user.familyName}</b></div>
            <div><i>email: ${user.email}</i></div>
            <div><i>id: ${user.id}</i></div>
        </c:forEach>
    </div>

<c:import url="../templates/footer.jsp" />

