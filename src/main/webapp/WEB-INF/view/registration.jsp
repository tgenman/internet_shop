<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="title" value="Registration form"  scope="request"/>
<c:set var="title2" value="Registration form"  scope="request"/>
<c:import url="templates/header.jsp" />


<div>
    <h3>
        <c:out value="${existMessage}" />
    </h3>
</div>


<c:set var="path" value="/registration"  scope="request"/>
<c:set var="buttonTitle" value="Sign Up"  scope="request"/>
<c:import url="templates/login_form.jsp" />


<c:import url="templates/footer.jsp" />
