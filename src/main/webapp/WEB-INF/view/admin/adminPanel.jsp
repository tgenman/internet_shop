<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="Home Page"  scope="request"/>
<%@ include file="../templates/header.jsp" %>

<h3>Admin Panel</h3>

<a class="btn btn-primary" href="/admin/product" role="button">Product List</a>
<a class="btn btn-primary" href="/admin/order" role="button">Order List</a>
<a class="btn btn-primary" href="/admin/user" role="button">User List</a>


<%@ include file="../templates/footer.jsp" %>