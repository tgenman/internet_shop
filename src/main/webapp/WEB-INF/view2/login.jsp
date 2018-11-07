<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="Login Page"  scope="request"/>
<c:set var="title2" value="Login Page"  scope="request"/>
<%@ include file="templates/header.jsp" %>


<%--<form:form method="POST" action="/login">--%>
    <%--<table>--%>
        <%--<tr class="form-group">--%>
            <%--<td><form:label path="username">Username</form:label></td>--%>
            <%--<td><form:input path="username" /></td>--%>
        <%--</tr>--%>
        <%--<tr class="form-group">--%>
            <%--<td><form:label path="password">Password</form:label></td>--%>
            <%--<td><form:input path="password" /></td>--%>
        <%--</tr>--%>
        <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
        <%--</tr>--%>
        <%--<tr class="form-group">--%>
            <%--<td><input type="submit" value="Sign In" /></td>--%>
        <%--</tr>--%>
    <%--</table>--%>
<%--</form:form>--%>

<div>
    <form action="/login" method="post">
        <table>
            <tr class="form-group">
                <td><label>Username</label></td>
                <td><input type="text" class="form-control" name="username"/></td>
            </tr>
            <tr class="form-group">
                <td><label>Password</label></td>
                <td><input type="text" class="form-control" name="password"/></td>
            </tr>
            <tr class="form-group">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </tr>
            <tr class="form-group">
                <td><button type="submit">Login</button></td>
            </tr>
        </table>
    </form>
</div>


<%@ include file="templates/footer.jsp" %>