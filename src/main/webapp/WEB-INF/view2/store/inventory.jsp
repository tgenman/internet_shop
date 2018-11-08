<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="Home Page"  scope="request"/>
<%@ include file="../templates/header.jsp" %>

<h2>Inventory</h2>
<a class="nav-link" href="/product/new">Create new product</a>


<%@ include file="../templates/footer.jsp" %>