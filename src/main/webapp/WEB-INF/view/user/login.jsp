<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="Login Page"  scope="request"/>
<c:import url="../templates/header.jsp" />


<c:set var="path" value="/user/login"  scope="request"/>
<c:set var="buttonTitle" value="Sign Ip"  scope="request"/>
<c:import url="../templates/login_form.jsp" />


<div>
    <a href="/user/registration">Registration</a>
</div>


<c:import url="../templates/footer.jsp" />

