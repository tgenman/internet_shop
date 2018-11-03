<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="Home Page"  scope="session"/>
<c:import url="templates/header.jsp" />

    <div> <a href="/user/registration">Registration</a> </div>
    <div> <a href="/user/login">Login</a> </div>
    <div> <a href="/user/all">All profiles</a> </div>
    <div>
        <form action="/logout" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="Sign Out"/>
        </form>
    </div>

<c:import url="templates/footer.jsp" />

