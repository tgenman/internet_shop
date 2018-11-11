<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="Home Page"  scope="request"/>
<%@ include file="templates/header.jsp" %>

<h5>
    Hello,
    <security:authorize access="isAuthenticated()">
        <security:authentication property="principal.username" />
    </security:authorize>
    <security:authorize access="!isAuthenticated()">
        Guest
    </security:authorize>
</h5>

<div>This is a simple shop</div>


<%@ include file="templates/footer.jsp" %>