<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="Login Page"  scope="request"/>
<c:set var="title2" value="Login Page"  scope="request"/>
<%@ include file="templates/header.jsp" %>


<form action="/login" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Email</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="username"/>
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" name="password"/>
        </div>
    </div>

    <div class="form-group">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </div>

    <div class="form-group row">
        <div class="col-sm-3">
            <button type="submit" class="btn btn-primary">Login</button>
        </div>
    </div>
</form>

<%@ include file="templates/footer.jsp" %>