<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="Home Page"  scope="request"/>
<%@ include file="../templates/header.jsp" %>

<h3>Admin Panel</h3>

<%--<a class="btn btn-primary" href="/admin/product" role="button">Product List</a>--%>
<%--<a class="btn btn-primary" href="/admin/order" role="button">Order List</a>--%>
<%--<a class="btn btn-primary" href="/admin/user" role="button">User List</a>--%>

<div class="btn-group-vertical btn-group-lg" role="group" aria-label="Basic example">
    <a class="btn btn-success" role="button" href="/admin/product">Product List</a>
    <a class="btn btn-success" role="button" href="/admin/order">Order List</a>
    <a class="btn btn-success" role="button" href="/admin/user">User List</a>
</div>


<%@ include file="../templates/footer.jsp" %>