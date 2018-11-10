<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="Create Address"  scope="request"/>
<%@ include file="../../templates/header.jsp" %>

<h2>Create Address</h2>

<form:form method="POST" action="/user/address/new" modelAttribute="addressDto">

    <div class="form-group row">
        <form:label path="postalCode" class="col-sm-2 col-form-label">Postal Code</form:label>
        <div class="col-sm-3">
            <form:input path="postalCode" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="postalCode"  />
    </div>

    <div class="form-group row">
        <form:label path="country" class="col-sm-2 col-form-label">Country</form:label>
        <div class="col-sm-3">
            <form:input path="country" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="country" />
    </div>

    <div class="form-group row">
        <form:label path="city" class="col-sm-2 col-form-label">City</form:label>
        <div class="col-sm-3">
            <form:input path="city" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="city" />
    </div>

    <div class="form-group row">
        <form:label path="street" class="col-sm-2 col-form-label">Street</form:label>
        <div class="col-sm-3">
            <form:input path="street" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="street"  />
    </div>

    <div class="form-group row">
        <form:label path="Building" class="col-sm-2 col-form-label">Building</form:label>
        <div class="col-sm-3">
            <form:input path="Building" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="Building"/>
    </div>

    <div class="form-group row">
        <form:label path="Flat" class="col-sm-2 col-form-label">Flat</form:label>
        <div class="col-sm-3">
            <form:input path="flat" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="flat"/>
    </div>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div class="form-group row">
        <div class="col-sm-3">
            <button type="submit" class="btn btn-success">Create</button>
        </div>
    </div>
</form:form>

<%@ include file="../../templates/footer.jsp" %>