<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Registration form"  scope="session"/>
<c:import url="../templates/header.jsp" />

    <div>
        <h3>
            <c:out value="${existMessage}" />
        </h3>
    </div>

    <div>
        <form method="post">
            <div class="form-group">
                <input type="text" class="form-control" name="email" placeholder="email" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="password" placeholder="password" />
            </div>
            <div class="form-group">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </div>

            <div class="form-group">
                <button type="submit">Register</button>
            </div>
        </form>
    </div>


<c:import url="../templates/footer.jsp" />
