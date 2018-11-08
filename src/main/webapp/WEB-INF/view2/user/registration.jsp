<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Add new product"     scope="request"/>
<c:set var="title2" value="Add new product"  scope="request"/>
<%@ include file="../templates/header.jsp" %>


<form:form method="POST" action="/user/registration" modelAttribute="productDto">

    <div class="form-group row">
        <form:label path="firstName" class="col-sm-2 col-form-label">First Name</form:label>
        <div class="col-sm-3">
            <form:input path="firstName" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="firstName" />
    </div>

    <div class="form-group row">
        <form:label path="lastName" class="col-sm-2 col-form-label">Last Name</form:label>
        <div class="col-sm-3">
            <form:input path="lastName" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="lastName" />
    </div>

    <div class="form-group row">
        <form:label path="email" class="col-sm-2 col-form-label">Email</form:label>
        <div class="col-sm-3">
            <form:input path="email" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="email"  />
    </div>

    <div class="form-group row">
        <form:label path="password" class="col-sm-2 col-form-label">Password</form:label>
        <div class="col-sm-3">
            <form:input path="password" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="password"  />
    </div>

    <div class="form-group row">
        <form:label path="matchingPassword" class="col-sm-2 col-form-label">Confirm password</form:label>
        <div class="col-sm-3">
            <form:input path="matchingPassword" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="matchingPassword"/>
    </div>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div class="form-group row">
        <div class="col-sm-3">
            <button type="submit" class="btn btn-primary">Sign in</button>
        </div>
    </div>
</form:form>


<%@ include file="../templates/footer.jsp" %>
