<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Registration page"     scope="request"/>
<c:set var="title2" value="Registration"  scope="request"/>
<%@ include file="../templates/header.jsp" %>


    <form:form method="POST" action="/user/registration" modelAttribute="userDto">
        <table>
            <tr>
                <td><form:label path="firstName">First Name</form:label></td>
                <td><form:input path="firstName" /></td>
                <td><form:errors path="firstName"  /></td>
            </tr>
            <tr>
                <td><form:label path="lastName">Last Name</form:label></td>
                <td><form:input path="lastName" /></td>
                <td><form:errors path="lastName"  /></td>
            </tr>
            <tr>
                <td><form:label path="email">Email</form:label></td>
                <td><form:input path="email" /></td>
                <td><form:errors path="email"  /></td>
            </tr>
            <tr>
                <td><form:label path="password">Password</form:label></td>
                <td><form:input path="password" /></td>
                <td><form:errors path="password"  /></td>
            </tr>
            <tr>
                <td><form:label path="matchingPassword">Confirm password</form:label></td>
                <td><form:input path="matchingPassword" /></td>
                <td><form:errors path="matchingPassword"  /></td>
            </tr>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </tr>
            <tr>
                <td><input type="submit" value="Register" /></td>
            </tr>
        </table>
    </form:form>


<%@ include file="../templates/footer.jsp" %>







<%--<h1 th:text="#{label.form.title}">form</h1>--%>
<%--<form action="/" th:object="${user}" method="POST" enctype="utf8">--%>
    <%--<div>--%>
        <%--<label th:text="#{label.user.firstName}">first</label>--%>
        <%--<input th:field="*{firstName}"/>--%>
        <%--<p th:each="error: ${#fields.errors('firstName')}"--%>
           <%--th:text="${error}">Validation error</p>--%>
    <%--</div>--%>
    <%--<div>--%>
        <%--<label th:text="#{label.user.lastName}">last</label>--%>
        <%--<input th:field="*{lastName}"/>--%>
        <%--<p th:each="error : ${#fields.errors('lastName')}"--%>
           <%--th:text="${error}">Validation error</p>--%>
    <%--</div>--%>
    <%--<div>--%>
        <%--<label th:text="#{label.user.email}">email</label>--%>
        <%--<input type="email" th:field="*{email}"/>--%>
        <%--<p th:each="error : ${#fields.errors('email')}"--%>
           <%--th:text="${error}">Validation error</p>--%>
    <%--</div>--%>
    <%--<div>--%>
        <%--<label th:text="#{label.user.password}">password</label>--%>
        <%--<input type="password" th:field="*{password}"/>--%>
        <%--<p th:each="error : ${#fields.errors('password')}"--%>
           <%--th:text="${error}">Validation error</p>--%>
    <%--</div>--%>
    <%--<div>--%>
        <%--<label th:text="#{label.user.confirmPass}">confirm</label>--%>
        <%--<input type="password" th:field="*{matchingPassword}"/>--%>
    <%--</div>--%>
    <%--<button type="submit" th:text="#{label.form.submit}">submit</button>--%>
<%--</form>--%>

<%--<a th:href="@{/login.html}" th:text="#{label.form.loginLink}">login</a>--%>
<%--</body>--%>
<%--</html>--%>