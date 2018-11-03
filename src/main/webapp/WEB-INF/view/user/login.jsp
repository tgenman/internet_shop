<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="Login Page"  scope="session"/>
<c:import url="../templates/header.jsp" />

    <div>
        <form action="/user/login" method="post">
            <div class="form-group">
                <input type="text" class="form-control" name="username" placeholder="username" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="password" placeholder="password" />
            </div>
            <div class="form-group">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </div>
            <div class="form-group">
                <input type="submit" value="submit" />
            </div>
        </form>
    </div>

    <div> <a href="/user/registration">Registration</a> </div>

<c:import url="../templates/footer.jsp" />

