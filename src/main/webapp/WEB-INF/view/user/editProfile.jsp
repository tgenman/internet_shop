<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="Edit Product"  scope="request"/>
<%@ include file="../templates/header.jsp" %>

<h2>Edit Product</h2>

<form:form method="POST" action="/user/" modelAttribute="userDto">

    <div class="form-group row">
        <form:label path="username" class="col-sm-2 col-form-label">Username</form:label>
        <div class="col-sm-3">
            <form:input path="username" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="username" />
    </div>

    <div class="form-group row">
        <form:label path="email" class="col-sm-2 col-form-label">Email</form:label>
        <div class="col-sm-3">
            <form:input path="email" class="form-control" id="inputEmail3" placeholder="email" />
        </div>
        <form:errors path="email" />
    </div>

    <div class="form-group row">
        <form:label path="password" class="col-sm-2 col-form-label">Password</form:label>
        <div class="col-sm-3">
            <form:input path="password" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="password"  />
    </div>

    <div class="form-group row">
        <form:label path="firstName" class="col-sm-2 col-form-label">First Name</form:label>
        <div class="col-sm-3">
            <form:input path="firstName" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="firstName"  />
    </div>

    <div class="form-group row">
        <form:label path="lastName" class="col-sm-2 col-form-label">Last Name</form:label>
        <div class="col-sm-3">
            <form:input path="lastName" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="lastName"/>
    </div>

    <div class="form-group row">
        <form:label path="dateOfBirth" class="col-sm-2 col-form-label">Date of Birth</form:label>
        <div class="col-sm-3">
            <form:input path="dateOfBirth" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="dateOfBirth"/>
    </div>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div class="form-group row">
        <div class="col-sm-3">
            <button type="submit" class="btn btn-warning btn-lg">Edit</button>
        </div>
    </div>
</form:form>

<h2>Addresses:</h2>

<c:forEach var="address" items="${userDto.addresses}">
    <div class="card" style="width: 18rem;">
        <div class="card-body">
            <h5 class="card-title">Address</h5>
            <p class="card-text">
                    ${address.postalCode}
                    ${address.country}
                    ${address.city}
                    ${address.street}
                    ${address.building}
                    ${address.flat}
            </p>
            <a href="/user/address/${address.id}" class="btn btn-warning">edit</a>
        </div>
    </div>
</c:forEach>



<form:form method="GET" action="/user/address/new" >
    <div class="form-group row">
        <div class="col-sm-3">
            <button type="submit" class="btn btn-danger">Add address</button>
        </div>
    </div>
</form:form>

<%@ include file="../templates/footer.jsp" %>