<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="Edit Product"  scope="request"/>
<%@ include file="../../templates/header.jsp" %>

<h2>Edit Product</h2>

<form:form method="POST" action="/admin/product/${productDto.id}" modelAttribute="productDto">

    <div class="form-group row">
        <form:label path="title" class="col-sm-2 col-form-label">Title</form:label>
        <div class="col-sm-3">
            <form:input path="title" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="title" />
    </div>

    <div class="form-group row">
        <form:label path="category" class="col-sm-2 col-form-label">Category</form:label>
        <div class="col-sm-3">
            <form:input path="category" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="category" />
    </div>

    <div class="form-group row">
        <form:label path="price" class="col-sm-2 col-form-label">Price</form:label>
        <div class="col-sm-3">
            <form:input path="price" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="price"  />
    </div>

    <div class="form-group row">
        <form:label path="weight" class="col-sm-2 col-form-label">Weight</form:label>
        <div class="col-sm-3">
            <form:input path="weight" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="weight"  />
    </div>

    <div class="form-group row">
        <form:label path="volume" class="col-sm-2 col-form-label">Volume</form:label>
        <div class="col-sm-3">
            <form:input path="volume" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="volume"/>
    </div>

    <div class="form-group row">
        <form:label path="quantity" class="col-sm-2 col-form-label">Quantity</form:label>
        <div class="col-sm-3">
            <form:input path="quantity" class="form-control" id="inputEmail3" />
        </div>
        <form:errors path="quantity"/>
    </div>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div class="form-group row">
        <div class="col-sm-3">
            <button type="submit" class="btn btn-primary">Edit</button>
        </div>
    </div>
</form:form>


<form:form method="DELETE" action="/admin/product/${productDto.id}" >
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div class="form-group row">
        <div class="col-sm-3">
            <button type="submit" class="btn btn-danger">Remove</button>
        </div>
    </div>
</form:form>

<%@ include file="../../templates/footer.jsp" %>